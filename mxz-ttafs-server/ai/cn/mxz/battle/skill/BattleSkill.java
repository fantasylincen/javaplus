package cn.mxz.battle.skill;

import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.team.Skill;
import db.domain.Skills;

/**
 *
 * 配置表里面读取出来的技能
 *
 * @author 林岑
 *
 */
public class BattleSkill extends AbstractSkill {

	private Skill	skill;

	public BattleSkill(Skill s) {
		super(s.getId());
		this.skill = s;
	}

	@Override
	public int getFighterId() {
		return attacker.getTypeId();
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getIdentification() {
		return 0;
	}

	@Override
	public int getShenJia() {
		SkillTemplet templet = SkillTempletConfig.get(getId());
		return (int) (templet.getSocial() + (getLevel() - 1) * templet.getSocialGrow());
	}

	@Override
	public Skills getDto() {
		return null;
	}

	@Override
	public int getLevel() {
		return skill.getLevel();
	}
}
