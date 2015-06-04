package cn.mxz.regist;

import java.util.List;


/**
 *
 * 每日签到Book
 *
 * @author 林岑
 *
 */
public interface Register {

	/**
	 * 签到
	 * @return
	 */
	void regist();

	/**
	 * 本月所有签到记录
	 *
	 * 本月有几天, 列表长度就有多长
	 *
	 * @return
	 */
	List<RegistRecord> getRecords();

	/**
	 * 补签
	 */
	void remedy();

	/**
	 * 今日是否签到
	 * @return
	 */
	boolean isRegistToday();

}