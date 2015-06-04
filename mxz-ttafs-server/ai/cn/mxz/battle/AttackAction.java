package cn.mxz.battle;

import java.util.List;

/**
 * 攻击动作
 * @author 林岑
 *
 */
public interface AttackAction {

	/**
	 * 技能作用点
	 * @return
	 */
	List<SkillPoint> getSkillPoints();

	/**
	 * 出手方是否在底下
	 * @return
	 */
	boolean isAttackerUnder();

	/**
	 * 技能ID
	 * @return
	 */
	int getSkillId();

	/**
	 * 当前回合
	 * @return
	 */
	int getRound();

	/**
	 * 攻击者位置
	 * @return
	 */
	int getPosition();

	/**
	 * 失效的buff列表
	 * @return
	 */
	List<BuffDisappear> getBuffDisappears();

	int getPartnerPosition();

	/**
	 * 是否是神兽出手
	 * @return
	 */
	boolean isDogz();

	int getUnderDogzAngryValue();

	int getUpperDogzAngryValue();

	/**
	 * 此次攻击是否是反击
	 * @return
	 */
	boolean isCounterAttack();

	void setIsCounterAttack(boolean isCounterAttack);
}
