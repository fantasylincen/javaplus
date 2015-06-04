package cn.mxz.tower;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.IFighterTemplet;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.AbstractFighter;
import cn.mxz.team.Skill;

import com.google.common.collect.Lists;

class BugFighterImpl extends AbstractFighter implements BugFighter {

	private FighterTemplet	temp;

	private int	hp;

	BugFighterImpl(FighterTemplet temp) {

		this.temp = temp;

		hp = temp.getHp();
	}

	@Override
	public int getTypeId() {

		return temp.getType();
	}

	@Override
	public int getLevel() {

		return temp.getLevel();
	}

	@Override
	public void reduceHp(int reduce) {

		this.hp -= reduce;
	}

	@Override
	public void addHp(int add) {

		hp += add;
	}

//	@Override
//	protected Fraction getActiveSkillExp() {
//
//		return new Fraction(1, 1);
//	}

//	@Override
//	protected int getActiveSkillLevel() {
//		return 1;
//	}

	@Override
	public int getHpNow() {

		return hp;
	}

	@Override
	protected float getNanDuXiShu() {
		return 1;
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public int getHpMax() {
		return getAttribute().getHp();
	}

	@Override
	public List<Skill> getSkills() {
		return Lists.newArrayList();
	}

	@Override
	protected IFighterTemplet getTemplet() {
		return temp;
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}
}
