package cn.mxz.battle.skill;

import cn.mxz.fighter.Fighter;


public interface Fire {

	/**
	 * 施放技能作用效果
	 */
	void fire();

	/**
	 * 施法方
	 * @return
	 */
	Fighter getAttacker();

}
