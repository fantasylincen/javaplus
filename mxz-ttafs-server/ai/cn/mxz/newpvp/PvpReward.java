package cn.mxz.newpvp;

/**
 * pvp奖励
 * @author 林岑
 *
 */
interface PvpReward {

	/**
	 * 是否领取了
	 * @return
	 */
	boolean hasReceived();

	/**
	 * 领取这个奖励
	 * @param rewardType 奖励ID
	 */
	void receive(int rewardType);

}
