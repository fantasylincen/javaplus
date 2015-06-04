package cn.mxz.loginreward;

public interface LoginRewardPlayer {

	/**
	 * 是否领取了今日奖励
	 *
	 * @return
	 */
	boolean hasReceive();

	/**
	 * 领取登陆元宝奖励
	 *
	 * @return
	 */
	Result receiveGoldReward();

	/**
	 * 连续领取次数
	 *
	 * @return
	 */
	int getContinuousDay();

	/**
	 * 翻开所有牌
	 *
	 * @return
	 */
	OpenResult openAllCard();

	/**
	 * 是否需要显示翻牌
	 * @return
	 */
	boolean isShowable();

}
