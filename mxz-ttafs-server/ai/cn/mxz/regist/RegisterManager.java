package cn.mxz.regist;

interface RegisterManager {

	/**
	 * 获得某天的奖励ID
	 * @param day	本月几号 1 - 31
	 * @return
	 */
	int getRewardId(int day);

}
