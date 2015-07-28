package cn.javaplus.common.util;

import java.util.Date;

public interface TimeChecker {

	/**
	 * 用于判断date是否在某个时间段�?
	 * @param date
	 * @param time	按照某种格式协定的时间格�?
	 * @return
	 */
	boolean isIn(Date date, String time);

}