package cn.mxz.bossbattle.battle;

import cn.mxz.skill.SkillDamageCalc;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.team.Skill;
import db.domain.Skills;

public class BossSkill implements Skill {

	private int	bossSkill;
	private BossFighter	bossFighter;

	public BossSkill(BossFighter bossFighter, int bossSkill) {
		this.bossFighter = bossFighter;
		this.bossSkill = bossSkill;
	}

	@Override
	public int getId() {
		return bossSkill;
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
		return bossFighter.getTypeId();
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
