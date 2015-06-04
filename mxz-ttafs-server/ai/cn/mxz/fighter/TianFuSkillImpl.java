package cn.mxz.fighter;

import cn.mxz.team.Skill;
import cn.mxz.user.team.god.ITianFuSkill;

public class TianFuSkillImpl implements ITianFuSkill {

	private Skill	skill;

	public TianFuSkillImpl(Skill skill) {
		this.skill = skill;
	}

	@Override
	public int getLevel() {
		return skill.getLevel();
	}

	@Override
	public int getId() {
		return skill.getId();
	}

}
