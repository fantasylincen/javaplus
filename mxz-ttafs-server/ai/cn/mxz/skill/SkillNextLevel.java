package cn.mxz.skill;

import cn.mxz.fighter.IdLevel;
import cn.mxz.team.Skill;

public class SkillNextLevel implements IdLevel {

	private Skill s;

	public SkillNextLevel(Skill s) {
		this.s = s;
	}

	@Override
	public int getId() {
		return s.getId();
	}

	@Override
	public int getLevel() {
		return s.getLevel() + 1;
	}

}
