package cn.mxz.shenmo;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.RandomEventBaseTemplet;
import cn.mxz.RandomEventBaseTempletConfig;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.AbstractFighter;
import cn.mxz.team.Skill;
import cn.mxz.user.team.god.ShenJiaAble;

import com.google.common.collect.Lists;

import db.domain.BossData;

public class ShenmoFighter extends AbstractFighter implements ShenJiaAble {

	private BossData	bd;
	private int			hpMax;

	public ShenmoFighter(BossData bd) {

		this.bd = bd;
		hpMax = getAttribute().getHp();
	}

	@Override
	public int getHpNow() {
		return bd.getHp();
	}

	@Override
	public List<Skill> getSkills() {
		return Lists.newArrayList();
	}

	@Override
	public int getHpMax() {
		return hpMax;
	}

	@Override
	public int getTypeId() {
		return bd.getBossTempletId();
	}

	@Override
	public int getLevel() {
		return bd.getLevel();
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public void reduceHp(int reduce) {
		bd.addHp(-reduce);
	}

	@Override
	public void addHp(int add) {
		bd.addHp(add);
	}

	@Override
	protected float getNanDuXiShu() {
		RandomEventBaseTemplet temp = RandomEventBaseTempletConfig.get(getLevel());
		Util.Exception.checkNull(temp, getLevel());
		return temp.getBossParam();
	}

	@Override
	public int getShenJia() {
		FighterTemplet temp = FighterTempletConfig.get(getTypeId());
		return (int) (temp.getSocial() + (getLevel() - 1) * temp.getSocialGrow());
	}

	@Override
	protected IFighterTemplet getTemplet() {
		return FighterTempletConfig.get(getTypeId());
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

}
