package util.timewheel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * 
 * 描述：一个基于时间hash的wheel超时器
 * 
 * 注意事项：
 * 1、所有的时间单位皆为毫秒
 * 
 * 创建时间：2012-6-4上午11:44:02
 * @author babi liu
 *
 */
public class HashedWheelTimer implements ITimer {

	private static final Logger					logger		= Logger.getLogger( HashedWheelTimer.class.getName() );

	private final int							ticksPerWheel;
	private final long							tickDuration;
	private final long							roundDuration;														// 走一整圈，所需要的时间
	private final List<HashedWheelTimeout>[]	wheel;
	private final ReadWriteLock					lock		= new ReentrantReadWriteLock();
	private final int							mask;
	private volatile int						wheelCursor	= 0;

	private final Thread						workerThread;														// 工作线程
	private final AtomicBoolean					shutdown	= new AtomicBoolean();

	public HashedWheelTimer() {
		// 缺省 一圈有512个ticks
		// 缺省 每100毫秒轮询一次（一个tick）
		this( 512, 10 );
		// this( 3, 1 );
	}

	/**
	 * 
	 * @param ticksPerWheel 一圈多少tick
	 */
	public HashedWheelTimer(int ticksPerWheel, long tickDuration) {
		this.ticksPerWheel = ticksPerWheel;
		this.tickDuration = tickDuration;
		wheel = createWheel( this.ticksPerWheel );
		mask = wheel.length - 1;
		roundDuration = tickDuration * wheel.length;
		workerThread = new Thread( new Worker(), "Hashed wheel timer thread" );
	}

	public void start () {
		workerThread.start();
	}

	@SuppressWarnings("unchecked")
	private List<HashedWheelTimeout>[] createWheel ( int ticksPerWheel ) {
		if (ticksPerWheel <= 0) {
			throw new IllegalArgumentException( "ticksPerWheel must be greater than 0: " + ticksPerWheel );
		}

		if (ticksPerWheel > 1073741824) {
			throw new IllegalArgumentException( "ticksPerWheel may not be greater than 2^30: " + ticksPerWheel );
		}

		// Normalize ticksPerWheel to power of two and initialize the wheel.
		ticksPerWheel = normalizeTicksPerWheel( ticksPerWheel );

		List<HashedWheelTimeout>[] wheel = new List[ticksPerWheel];

		for (int i = 0; i < wheel.length; i++) {

			wheel[i] = new LinkedList<HashedWheelTimeout>();
			// List list = Collections.synchronizedList( new
			// LinkedList<HashedWheelTimeout>() );同步版本，是否有必要？

			// ConcurrentIdentityHashMap<HashedWheelTimeout, Boolean>(16, 0.95f,
			// 4) );
			// wheel[i] = new MapBackedSet<HashedWheelTimeout>( new
			// ConcurrentHashMap<HashedWheelTimeout, Boolean>(16, 0.95f, 4) );
		}
		return wheel;
	}

	/**
	 * 添加一个任务到定时器当中
	 * 
	 * @param task
	 * @param delayMillis		延迟的毫秒数，一旦超过此毫秒数，将会执行此任务
	 * @return
	 */
	@Override
	public ITimeout newTimeout ( ITimerTask task, long delayMillis ) {

		if (task == null) {
			throw new NullPointerException( "task" );
		}

		final long currentTime = System.currentTimeMillis();
		HashedWheelTimeout timeout = new HashedWheelTimeout( task, currentTime + delayMillis );
		// System.out.println( "timeout.deadline：" + timeout.deadline +
		// "(currentTime + delayMillis:" + currentTime + "+" + delayMillis + ")"
		// );
		scheduleTimeout( timeout, delayMillis );
		return timeout;
	}

	/**
	 * 把一个新的timeout事件插入到事件队列中
	 * 
	 * @param timeout
	 * @param delayMillis
	 */
	private void scheduleTimeout ( HashedWheelTimeout timeout, long delayMillis ) {
		// delay must be equal to or greater than tickDuration so that the
		// worker thread never misses the timeout.
		if (delayMillis < tickDuration) {
			delayMillis = tickDuration;
		}

		// Prepare the required parameters to schedule the timeout object.
		final long lastRoundDelay = delayMillis % roundDuration;// 计算小圈内总共所需要的时间,没有转换为具体的桶的数目（最外层的大圈下面考虑）20
		final long lastTickDelay = delayMillis % tickDuration;// 计算小圈内是否需要加额外1个桶，如果正好除尽则不需要额外加0
		final long relativeIndex = lastRoundDelay / tickDuration + (lastTickDelay != 0 ? 1 : 0);// 准确计算小圈内的桶

		final long remainingRounds = delayMillis / roundDuration - (delayMillis % roundDuration == 0 ? 1 : 0);// 计算需要最外层所需要的大圈数

		// Add the timeout to the wheel.
		lock.readLock().lock();
		try {
			int stopIndex = (int) (wheelCursor + relativeIndex & mask);// 根据当前所在的桶增加此事件到适当位置
			timeout.stopIndex = stopIndex;
			timeout.remainingRounds = remainingRounds;

			wheel[stopIndex].add( timeout );
		} finally {
			lock.readLock().unlock();
		}

		// if( timeout.deadline > start)
	}

	/**
	 * 确保ticksPerWheel为2的n次方，这是为了确保以下代码能正常运行
	 * int newWheelCursor = wheelCursor = wheelCursor + 1 & mask;
	 * 如不理解，可将mask修改二进制以0（2,4等）结尾的数字进行测试
	 * 
	 * @param ticksPerWheel
	 * @return
	 */
	private static int normalizeTicksPerWheel ( int ticksPerWheel ) {

		int normalizedTicksPerWheel = 1;
		while (normalizedTicksPerWheel < ticksPerWheel) {
			normalizedTicksPerWheel <<= 1;
		}
		return normalizedTicksPerWheel;
	}

	private final class Worker implements Runnable {

		private long	startTime;
		private long	tick;

		Worker() {
			super();
		}

		public void run () {
			List<HashedWheelTimeout> expiredTimeouts = new ArrayList<HashedWheelTimeout>();

			startTime = System.currentTimeMillis();
			// System.out.println( "startTime = " + startTime );
			tick = 1;

			while (!shutdown.get()) {
				final long deadline = waitForNextTick();
				// System.out.println( "deadline = " + deadline );
				if (deadline > 0) {
					fetchExpiredTimeouts( expiredTimeouts, deadline );
					notifyExpiredTimeouts( expiredTimeouts );
				}
			}
		}

		private void fetchExpiredTimeouts ( List<HashedWheelTimeout> expiredTimeouts, long deadline ) {

			// Find the expired timeouts and decrease the round counter
			// if necessary. Note that we don't send the notification
			// immediately to make sure the listeners are called without
			// an exclusive lock.
			lock.writeLock().lock();
			try {
				wheelCursor = wheelCursor + 1 & mask;
				// IReusableIterator<HashedWheelTimeout> i =
				// iterators[newWheelCursor];
				fetchExpiredTimeouts( expiredTimeouts, wheelCursor, deadline );
			} finally {
				lock.writeLock().unlock();
			}
		}

		private void fetchExpiredTimeouts ( List<HashedWheelTimeout> expiredTimeouts, int cursor, long deadline ) {

			// List<HashedWheelTimeout> slipped = null;
			Iterator<HashedWheelTimeout> i = wheel[cursor].iterator();
			while (i.hasNext()) {
				HashedWheelTimeout timeout = i.next();
				if (timeout.remainingRounds <= 0) {
					i.remove();
					// if (timeout.deadline <= deadline) {
					expiredTimeouts.add( timeout );
					// } else {
					// // Handle the case where the timeout is put into a wrong
					// // place, usually one tick earlier. For now, just add
					// // it to a temporary list - we will reschedule it in a
					// // separate loop.
					// if (slipped == null) {
					// slipped = new ArrayList<HashedWheelTimeout>();
					// }
					// System.out.println( timeout + "--timeout.deadline:" +
					// timeout.deadline + ",deadline:" + deadline );
					// slipped.add( timeout );
					// }
				} else {
					timeout.remainingRounds--;
				}
			}

			// // Reschedule the slipped timeouts.
			// if (slipped != null) {
			//
			// for (HashedWheelTimeout timeout: slipped) {
			// scheduleTimeout( timeout, timeout.deadline - deadline );
			// }
			// }
		}

		private void notifyExpiredTimeouts ( List<HashedWheelTimeout> expiredTimeouts ) {
			// Notify the expired timeouts.

			// for (int i = expiredTimeouts.size() - 1; i >= 0; i --) {
			// expiredTimeouts.get(i).expire();
			// }
			//

			for (HashedWheelTimeout timeout : expiredTimeouts) {
				timeout.expire();
			}

			// Clean up the temporary list.
			expiredTimeouts.clear();
		}

		private long waitForNextTick () {
			long deadline = startTime + tickDuration * tick;

			for (;;) {
				final long currentTime = System.currentTimeMillis();
				final long sleepTime = tickDuration * tick - (currentTime - startTime);

				if (sleepTime <= 0) {
					break;
				}

				try {
					Thread.sleep( sleepTime );
				} catch (InterruptedException e) {
					if (shutdown.get()) {
						return -1;
					}
				}
			}

			// Increase the tick.
			tick++;
			return deadline;
		}
	}

	private final class HashedWheelTimeout implements ITimeout {

		private final ITimerTask	task;
		final long 					deadline;
		volatile int				stopIndex;
		volatile long				remainingRounds;
		private volatile boolean	cancelled;

		HashedWheelTimeout(ITimerTask task, long deadline) {
			this.task = task;
			this.deadline = deadline;
		}

		public ITimer getTimer () {
			return HashedWheelTimer.this;
		}

		public ITimerTask getTask () {
			return task;
		}

		public void cancel () {
			if (isExpired()) {
				return;
			}

			cancelled = true;

			// Might be called more than once, but doesn't matter.
			wheel[stopIndex].remove( this );
		}

		public boolean isCancelled () {
			return cancelled;
		}

		public boolean isExpired () {
			return cancelled/* || System.currentTimeMillis() > deadline*/;
		}

		public void expire () {
			if (cancelled) {
				return;
			}

			try {
				long s = System.currentTimeMillis() - deadline; 
				if( s > 0 ){
					System.out.println( "timeout：" + s );
				}
				task.run( this );
			} catch (Throwable t) {
				logger.warning( "An exception was thrown by " + ITimerTask.class.getSimpleName() + "." + t );
			}
		}

		@Override
		public String toString () {
			// long currentTime = System.currentTimeMillis();
			// long remaining = deadline - currentTime;

			StringBuilder buf = new StringBuilder( 192 );
			buf.append( getClass().getSimpleName() );
			buf.append( '(' );

			buf.append("deadline: ");
			buf.append( deadline );
			// if (remaining > 0) {
			// buf.append(remaining);
			// buf.append(" ms later, ");
			// } else if (remaining < 0) {
			// buf.append(-remaining);
			// buf.append(" ms ago, ");
			// } else {
			// buf.append("now, ");
			// }

			if (isCancelled()) {
				buf.append( ", cancelled" );
			}

			return buf.append( ')' ).toString();
		}
	}

	@Override
	public Set<ITimeout> stop () {
		return null;
	}

	public static void main ( String[] args ) {
		int count = 8;
		int mask = count - 1;
		int n = 0;
		for (int i = 0; i < 20; i++) {

			n = n + 1 & mask;
			System.out.println( n );
		}
	}
}