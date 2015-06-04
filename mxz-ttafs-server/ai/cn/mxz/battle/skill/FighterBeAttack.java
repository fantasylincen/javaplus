package cn.mxz.battle.skill;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.fighter.Fighter;

/**
 *
 * 被技能打中的战士
 *
 * @author 林岑
 *
 */
public interface FighterBeAttack {

	/**
	 * 伤害衰减值
	 *
	 * 实际伤害 = damage * getDecay
	 *
	 * @return
	 */
	float getDecay();

	/**
	 * 设定目标战士
	 * @param fighter
	 */
	void setTarget(Fighter fighter);

	/**
	 * 设定衰减比例
	 * @param i
	 */
	void setDecay(float decay);

	/**
	 * 被技能作用前的属性
	 * @return
	 */
	Attribute getOld();

	/**
	 * 设置被技能作用前的属性
	 * @param old
	 */
	void setAttributeOld(Attribute old);

	/**
	 * 历史BufferId列表
	 * @return
	 */
	List<Integer> getBufferIdsOld();

	boolean addBufferId(Integer bufferId);

	/**
	 * 目标战士
	 * @return
	 */
	Fighter getTarget();

	boolean isCrit();

	boolean isHit();

	boolean isBlock();

	void setHit(boolean b);
	
	void setBlock(boolean b);
	void setCrit(boolean b);
}
