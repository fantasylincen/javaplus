package cn.mxz.chuangzhen;

/**
 * 战斗奖励
 * @author 林岑
 *
 */
public interface ChuangZhenReward {

	/**
	 * 是否有 加成属性
	 * @return
	 */
	boolean getHasBtAddition();

	/**
	 * 加成属性
	 */
	BattleAddition getBattleAddition();

	/**
	 * 是否有 渡劫奖励
	 * @return
	 */
	boolean getHasBtReward();

	/**
	 * 渡劫奖励
	 * @return
	 */
	BattleReward getBattleReward();
}
