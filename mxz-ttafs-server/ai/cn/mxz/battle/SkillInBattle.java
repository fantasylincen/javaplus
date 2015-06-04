package cn.mxz.battle;

import java.util.List;

import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;
import cn.mxz.team.Skill;


/**
 * 战士主动技能(释放到战场上的技能)
 * @author 林岑
 */
public interface SkillInBattle extends Skill {

	/**
	 * 获得技能释放者
	 * @return
	 */
	Fighter getFighter();

	/**
	 * 施法者
	 * @param fighter
	 */
	void setAttacker(Fighter fighter);

	/**
	 * 战场
	 * @param battle
	 */
	void setBattle(Battle battle);

	/**
	 * 施放
	 * @return	被击中的战士列表
	 */
	List<FighterBeAttack> fire();
}
