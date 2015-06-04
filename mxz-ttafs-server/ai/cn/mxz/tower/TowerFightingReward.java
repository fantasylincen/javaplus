package cn.mxz.tower;

/**
 * 最后一场战斗奖励
 * @author 林岑
 *
 */
interface TowerFightingReward {

	/**
	 * 攻击积分奖励
	 * @return
	 */
	int getScore();

	/**
	 * 捕获积分奖励
	 * @return
	 */
	int getCatchScore();

	/**
	 * 增加捕获神将积分
	 * @param calcCatchScore
	 */
	
	void addCatchScore(int calcCatchScore);

}
