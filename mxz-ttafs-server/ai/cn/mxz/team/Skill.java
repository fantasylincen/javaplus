package cn.mxz.team;

import cn.mxz.fighter.IdLevel;
import cn.mxz.fighter.StepLevel;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.user.team.god.ShenJiaAble;
import db.domain.Skills;


/**
 * 神将技能
 * @author 林岑
 */
public interface Skill extends StepLevel, ShenJiaAble, IdLevel {

	/**
	 * 技能最大等级
	 * @return
	 */

	int getMaxLevel();

//	/**
//	 * 技能经验
//
//	 * @return
//	 */
//	Fraction getExp();

	/**
	 * 该技能是否装备在神将身上
	 * @return
	 */
	boolean isEquipment();

	/**
	 * 所属战士ID
	 * @return
	 */
	int getFighterId();

	/**
	 * 身价
	 * @return
	 */
	int getPrice();

	Addition getDamage();

	/**
	 * 技能唯一标识
	 * @return
	 */
	int getIdentification();

	Skills getDto();

	Addition getDamageNext();
}
