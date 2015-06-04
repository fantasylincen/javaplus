package cn.mxz.skill;

import java.util.List;

import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.fighter.Fighter;
import cn.mxz.team.Skill;

import com.google.common.collect.Lists;

import db.domain.Skills;

final class NormalAttack extends AbstractSkill implements Skill {

	private final Fighter	fighter;

	NormalAttack(Fighter fighter) {
		super(fighter.getNormalSkillId());
		this.fighter = fighter;
	}

	@Override
	public int getShenJia() {
		return 0;
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getIdentification() {
		return 1;
	}

	@Override
	public int getFighterId() {
		return fighter.getTypeId();
	}

	@Override
	public Skills getDto() {
		return null;
	}

	@Override
	public List<FighterBeAttack> fire() {
		return Lists.newArrayList();
	}
}