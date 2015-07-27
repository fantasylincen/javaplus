package cn.javaplus.common.util;


public class Time {

	/** ä¸?ˆ†é’Ÿçš„æ¯«ç§’æ•?*/
	public static final long MILES_ONE_MIN = 60 * 1000;

	/** ä¸?°æ—¶çš„æ¯«ç§’æ•?*/
	public static final long MILES_ONE_HOUR = 60 * MILES_ONE_MIN;

	/** ä¸?¤©çš„æ¯«ç§’æ•° */
	public static final long MILES_ONE_DAY = 24 * MILES_ONE_HOUR;

	private int h;
	private int m;
	private int s;

	/**
	 * æ—?
	 */
	public int getH() {
		return h;
	}

	/**
	 * åˆ?
	 */
	public int getM() {
		return m;
	}

	/**
	 * ç§?
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
