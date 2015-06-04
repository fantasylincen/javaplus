package cn.mxz.chuangzhen;

import cn.mxz.skill.SkillDamageCalc;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.team.Skill;
import db.domain.Skills;

public class ChuangZhenDemonSkill implements Skill {

	private int				skill;
	private ChuangZhenDemon	c;

	public ChuangZhenDemonSkill(ChuangZhenDemon c, int skill) {
		this.c = c;
		this.skill = skill;
	}

	@Override
	public int getId() {
		return skill;
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public int getStep() {
		return 1;
	}

	@Override
	public int getShenJia() {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean isEquipment() {
		return true;
	}

	@Override
	public int getFighterId() {
		return c.getTypeId();
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public Addition getDamage() {
		return new SkillDamageCalc().calc(this);
	}

	@Override
	public Addition getDamageNext() {
		return new SkillDamageCalc().calcNext(this);
	}

	@Override
	public Skills getDto() {
		return null;
	}

	@Override
	public int getIdentification() {
		return -1;
	}

}
