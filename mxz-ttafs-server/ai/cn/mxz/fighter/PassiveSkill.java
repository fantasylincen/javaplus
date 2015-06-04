package cn.mxz.fighter;

import cn.mxz.Attribute;
import cn.mxz.team.Skill;

/**
 * 被动技能
 * @author 林岑
 *
 */
public interface PassiveSkill extends Skill {

	/**
	 * 被动技能加成
	 * @return
	 */
	Attribute getAddition();
}
