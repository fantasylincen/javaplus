package cn.mxz.battle;

import cn.mxz.bossbattle.Prize;

/**
 *
 * 战斗经验奖励
 * @author 林岑
 *
 */
public interface BattleExpPrize extends Prize {

	/**
	 * 神将经验奖励
	 * @return
	 */
	int getExp();

	/**
	 * 神将ID
	 * @return
	 */
	int getFighterId();

	/**
	 * 该奖励发放后(调用award方法后)神将提升的等级数
	 * @return
	 */
	int getLevelAdd();

	/**
	 * 升级所需经验
	 * @return
	 */
	int getExpNeed();

}
