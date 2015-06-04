package cn.mxz.activity.boss;

interface BossReward {

	/**
	 * 击杀Boss后所获得的积分奖励
	 * 
	 * @return
	 */
	int getAttackReward();

	/**
	 * 连击奖励:当Boss攻击人数大于6的时候, 获得的积分奖励加成
	 * 
	 * 实际积分 = 积分 * (1 + getContinuous)
	 * 
	 * @return
	 */
	double getContinuous();

	/**
	 * 伤害奖励, 每次攻击Boss获得的积分奖励
	 * 
	 * @return
	 */
	int getDamageReward();

	/**
	 * 活动好友加成: 当攻击Boss的挑战者列表中有活动好友时 获得的加成
	 * 
	 * 实际积分 = 积分 * (1 + getFriendAddition)
	 * 
	 * @return
	 */
	double getFriendAddition();

	/**
	 * MVP 奖励加成
	 * 
	 * 实际积分 = 积分 * (1 + getMvp)
	 * 
	 * @return
	 */
	double getMvp();

	/**
	 * @return "MVP" || "JMVP"
	 */
	String getMVPOrJMVP();

	/**
	 * 活动好友加成(value)
	 * 
	 * @return
	 */
	int getFriendCount();

	/**
	 * 连击奖励(value) :当前攻击BOss的人数
	 * 
	 * @return
	 */
	int getChallengerCount();

	/**
	 * BossEventIntegralVo的ID, 如果没有获得积分奖励, 返回-1
	 * 
	 * @return
	 */
	int getScoreRewardId();
}
