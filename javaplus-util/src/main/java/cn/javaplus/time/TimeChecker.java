package cn.javaplus.time;

import java.util.Date;

public interface TimeChecker {

	/**
	 * 用于判断date是否在某个时间段内
	 * @param date
	 * @param time	按照某种格式协定的时间格式
	 *
	 * 支持:
	 * 00:00 to 24:00
	 * 00:00 to 24:00
	 * 00:00 to 24:00
	 * 14:00 MONDAY to 14:00 FRIDAY
	 * 00:00 to 24:00
	 * 00:00 to 24:00
	 * 00:00 to 24:00
	 * 00:00 to 24:00
	 * 13:00 to 14:00 or 19:30 to 20:30 or 21:30 to 22:00
	 * 2013-09-25|14:00 to 2013-10-25|14:00
	 * @return
	 */
	boolean isIn(Date date, String time);

}