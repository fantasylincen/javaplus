package util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 通过一个独立的线程提供系统时间，减少系统调用
 * @author DXF
 *
 */
public class SystemTimer {
	private static final ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

	private SystemTimer() {
	};

	private final static int TICK_UNIT = 50;

	private static class TimerTicker implements Runnable {

		@Override
		public void run() {
			
		}

	}

	static {
		s.scheduleAtFixedRate( new TimerTicker(), TICK_UNIT, TICK_UNIT,
				TimeUnit.MILLISECONDS);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// System.out.println( "system will down");
				s.shutdown();

			}
		});
	}

	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	public static int currentTimeSecond() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			System.out.println(currentTimeMillis());
			Thread.sleep(1000);
		}
	}
}
