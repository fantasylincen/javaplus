package cn.javaplus.time;


public class Time {

	/** 一分钟的毫秒数 */
	public static final long MILES_ONE_MIN = 60 * 1000;

	/** 一小时的毫秒数 */
	public static final long MILES_ONE_HOUR = 60 * MILES_ONE_MIN;

	/** 一天的毫秒数 */
	public static final long MILES_ONE_DAY = 24 * MILES_ONE_HOUR;

	private int h;
	private int m;
	private int s;

	/**
	 * 时
	 */
	public int getH() {
		return h;
	}

	/**
	 * 分
	 */
	public int getM() {
		return m;
	}

	/**
	 * 秒
	 */
	public int getS() {
		return s;
	}

	public Time(long time) {
		h = (int) (time / 3600 % 60);
		m = (int) (time / 60 % 60);
		s = (int) (time % 60);
	}
}
