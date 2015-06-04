package cn.mxz.util;


/**
 * 时钟
 * @author 林岑
 */
public class ClockImpl implements Clock{

	private static final long STOP = 0;

	private long start;

	private long time;

	@Override
	public void start() {

		start = System.nanoTime();
	}

	@Override
	public void stop() {

		if(!isStart()) {

			return;
		}

		time += System.nanoTime() - start;

		start = STOP;
	}

	@Override
	public boolean isStart() {

		return start != STOP;
	}

	@Override
	public long getTime() {

		return time;
	}

	@Override
	public long getMillis() {
		return time / 1000000;
	}

	@Override
	public void clear() {

		time = 0;

		start = STOP;
	}
}
