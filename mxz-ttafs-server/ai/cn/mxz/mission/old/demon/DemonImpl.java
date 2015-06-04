package cn.mxz.mission.old.demon;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.FighterTemplet;
import cn.mxz.IFighterTemplet;
import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTemplet;
import cn.mxz.SkillTemplet;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.AbstractFighter;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeSetable;
import cn.mxz.skill.SkillDamageCalc;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.team.Skill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import db.domain.Skills;
import define.D;

public class DemonImpl extends AbstractFighter implements Demon {

	public class DemonSkill implements Skill {

		private int skill;

		public DemonSkill(int skill) {
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
			return 0;
		}

		@Override
		public int getMaxLevel() {
			return D.MAX_SKILL_LEVEL;
		}

		@Override
		public boolean isEquipment() {
			return true;
		}

		@Override
		public int getFighterId() {
			return temp.getId();
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
		public int getIdentification() {
			return getId();
		}

		@Override
		public Skills getDto() {
			return null;
		}

	}

	private FighterTemplet temp;

	private int hp;

	private MapTemplet mapTemp;

	/**
	 * 触发了伪概率的回合数
	 */
	private int roundWeiGaiLv = -1;

	private boolean isMain;

	private City city;

	/**
	 * 
	 * @param temp
	 * @param mapTemp
	 * @param isMain
	 * @param isBoss
	 * @param isDodge
	 * @param isWeiGaiLv
	 *            是否允许Boss技能伪概率
	 */
	public DemonImpl(FighterTemplet temp, MapTemplet mapTemp, boolean isBoss,
			boolean isMain, City city) {
		this.temp = temp;
		this.mapTemp = mapTemp;
		this.isMain = isMain;
		this.city = city;
		hp = getAttribute().getHp();
		if (hp < 0) {
			throw new RuntimeException("小怪气血为负数:" + hp);
		}
	}

	@Override
	public Attribute getAttribute() {
		return super.getAttribute();
	}

	private void initWeiGaiLv() {
		JSONObject o = JSON.parseObject(D.SKILL_WEI_GAI_LV);

		int mapMin = o.getInteger("mapMin");
		int mapMax = o.getInteger("mapMax");
		int round = o.getInteger("round");

		int id = mapTemp.getId();
		if (id >= mapMin && id <= mapMax) {
			roundWeiGaiLv = Util.Random.get(1, round);
		}
	}

	public IFighterTemplet getFighterTemplet() {
		return temp;
	}

	@Override
	public int getTypeId() {
		return temp.getType();
	}

	@Override
	public int getLevel() {
		return mapTemp.getMonsterGrade();
		// return temp.getLevel();
	}

	@Override
	public void reduceHp(int reduce) {
		hp -= reduce;
	}

	@Override
	public void addHp(int add) {

		hp += add;

		if (hp > getAttribute().getHp()) {

			hp = getAttribute().getHp();
		}

	}

	@Override
	protected boolean isSkillHappen(List<SkillTemplet> temps, Battle battle) {

		boolean skillHappen = super.isSkillHappen(temps, battle);

		if (battle instanceof MissionBattle) {

			MissionBattle b = (MissionBattle) battle;

			if (b.isBoss()) {
				int r = battle.getRound();

				if (r == 1) {
					initWeiGaiLv();
				}

				if (!skillHappen) {
					return r == roundWeiGaiLv;
				}
			}

		}

		return skillHappen;
	}

	// @Override
	// protected int getActiveSkillLevel() {
	// return 1;
	// }
	//
	// @Override
	// protected Fraction getActiveSkillExp() {
	//
	// return new Fraction(1, 1);
	// }

	@Override
	public String toString() {
		AttributeSetable copy = AttributeCalculator.copy(temp);
		return "DemonImpl [= 属性:" + copy + ", hp=" + hp + ", mapTemp="
				+ mapTemp + "]";
	}

	@Override
	public int getHpNow() {
		return hp;
	}

	@Override
	protected float getNanDuXiShu() {
		// 主线小怪难度系数 支线小怪难度系数 boss难度系数 支线boss难度系数
		// 0 0 0 0
		// float float float float
		// wilsonParam lineWilsonParam bossParam lineBossParam

		int c = temp.getCategory();

		boolean isBossFighter = c == 2;
		float f;
		if (isBossFighter) {
			f = isMain ? mapTemp.getBossParam() : mapTemp.getLineBossParam();
		} else {
			f = isMain ? mapTemp.getWilsonParam() : mapTemp
					.getLineWilsonParam();
		}
		
		int levelD = getLevel() - city.getLevel();

		if (levelD < 0) {
			levelD = 0;
		}

		return (float) (f + D.FU_BEN_GUAN_KA_FACTOR_ADD * levelD);

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
		List<Skill> ls = Lists.newArrayList();
		if (temp.getSkill() != 0 && temp.getSkill() != -1) {

			ls.add(new DemonSkill(temp.getSkill()));
		}

		return ls;
	}

	@Override
	public int getShenJia() {
		return (int) (temp.getSocial() + (getLevel() - 1)
				* temp.getSocialGrow());
	}

	@Override
	protected IFighterTemplet getTemplet() {
		return temp;
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

	@Override
	public int getLowerestDamage() {
		if (mapTemp instanceof MissionMapTemplet) {
			return (int) (((MissionMapTemplet) mapTemp).getHurtMin() * getPar());
		}
		return super.getLowerestDamage();
	}
}
