package cn.mxz.coutinuous;

import java.util.List;

public interface ContinuousManager {

	/**
	 * 今日操作了
	 * @param type
	 */
	void doIt(ContinuousType type);

	/**
	 * 获得连续操作次数
	 *
	 * 比如今日 为 星期5
	 *
	 * 星期1 - 星期5的状态依次为: 1 0 0 1 1 结果为 2
	 *
	 * @param type
	 * @return
	 */
	int getContinuous(ContinuousType type);

	/**
	 * 获取中途间隔了多少天
	 *
	 * 比如今日 为 星期5
	 *
	 * 星期1 - 星期5的状态依次为: 1 0 0 1 1 结果为 0
	 *
	 * 星期1 - 星期5的状态依次为: 1 0 0 0 0 结果为 4
	 *
	 * @param type
	 * @return
	 */
	int getSpaceDays(ContinuousType type);

	/**
	 * 获取上一个间隔 连续空闲的天数
	 * @param type
	 * @return
	 */
	int getPreviousSpace(ContinuousType type);

	/**
	 * 获得 前day天的操作情况
	 *
	 * 比如 day = 3 今天星期五, 那么返回  星期3, 星期4, 星期5 的状态
	 *
	 * @param day
	 * @return 有可能出现返回值长度 小于 day的情况
	 */
	List<Boolean> getStatus(ContinuousType type, int day);
}
