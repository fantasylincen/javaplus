package cn.mxz.battle;

import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;

/**
 * 战场
 * 
 * @author 林岑
 * @since 2013年6月6日 17:11:26
 * 
 */
public interface Battle {

	/**
	 * @return 攻击方是否打赢
	 */
	boolean isWin();

	/**
	 * 开打
	 */
	void fighting();

	/**
	 * 战况信息
	 * 
	 * @return
	 */
	WarSituation getWarSituation();

	/**
	 * 下方玩家阵营
	 */
	PlayerCamp getUnderPlayerCamp();

	/**
	 * 上方玩家阵营
	 */
	PlayerCamp getUpperPlayerCamp();

	/**
	 * 获得释放者的敌人阵营
	 * 
	 * @param fighter
	 * @return
	 */
	BattleCamp getEnemy(Fighter fighter);

	/**
	 * 友方阵营
	 * 
	 * @param attacker
	 * @return
	 */
	BattleCamp getFriends(Fighter attacker);

	/**
	 * 当前回合数 从1开始
	 * 
	 * @return
	 */
	int getRound();

	BattleCamp getUpper();

	BattleCamp getUnder();
}
