package cn.mxz.battle.buffer;

import cn.mxz.Attribute;
import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituation;


/**
 * 战斗技能Buffer
 * @author 林岑
 *
 */
public interface Buffer {

	/**
	 * Buffer id
	 * @return
	 */
	int getId();

	/**
	 * 是否可出手攻击
	 * @return
	 */
	boolean canHit();

	/**
	 * Buff是否已经失效
	 * @return
	 */
	boolean isLose();

	/**
	 * 进入新回合
	 * @param situation 
	 * @param currentRound 当前回合数
	 */
	void newRound(WarSituation situation, int currentRound);

	/**
	 * Buff 加成
	 * @return
	 */
	Attribute getAddition();

	/**
	 * 技能前
	 * @param battle
	 */
	void beforeSkill(Battle battle);

	boolean isNormalAttackOnly();

	long getIdfen();

	boolean isUnder();

	int getPosition();
}
