package cn.mxz.regist;

/**
 * 签到记录
 * @author 林岑
 *
 */
interface RegistRecord {

	/**
	 * 这条签到记录是否已经成为过去
	 *
	 * 是否已经被签过到, 或者是昨天及昨天以前的记录
	 *
	 * @return
	 */
	boolean isPast();

	/**
	 * 是否已经被签过到
	 * @return
	 */
	boolean hasReceived();

	/**
	 * 奖励ID
	 * @return
	 */
	int getRewardId();

	/**
	 * 该记录是本月的第几天
	 * @return
	 */
	int getDayOfMonth();

	/**
	 * 标记已经被领奖了
	 */
	void markSignIn();

}
