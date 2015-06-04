package cn.mxz.skill;

import java.util.Collection;
import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.battle.buff.AttributeSingle;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.Additions;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.IdLevel;
import cn.mxz.fighter.PassiveSkill;
import cn.mxz.formation.AdditionType;
import cn.mxz.protocols.user.god.FighterP.FighterSkillPro;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.team.Skill;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

import db.domain.Skills;
import define.D;

public class SkillFactory {

	public static Skill createNormalSkill(final Fighter fighter) {
		return new NormalAttack(fighter);
	}

	public static Skill create(Skills s) {
		int skillId = s.getSkillId();
		SkillTemplet st = SkillTempletConfig.get(skillId);
		if (st == null) {
			throw new NullPointerException(skillId + "");
		}
		if (st.getReleaseType() == 3) {
			return new PassiveSkillImpl(s, st);
		} else {
			return new ActiveSkill(s, st);
		}
	}

	/**
	 * 被动技能
	 *
	 * @author 林岑
	 *
	 */
	private static class PassiveSkillImpl extends ActiveSkill implements
			PassiveSkill {


		private String	uname;

		public PassiveSkillImpl(Skills dto, SkillTemplet temp) {
			super(dto, temp);
			uname = dto.getUname();
		}

		@Override
		public Attribute getAddition() {

			Hero hero = getHero();
			if (hero == null) {
				return new AttributeEmpty();
			}

			Attribute base = hero.getAdditions().getBase2();
			Attribute a = extracted(temp.getId(), getLevel(), base);
			return AttributeCalculator.adding(a, getTianMingAddition());
		}

		private Hero getHero() {
			City city = CityFactory.getCity(uname);
			Team team = city.getTeam();
			Hero hero = team.get(dto.getFighterTypeId());
			return hero;
		}

		private Attribute getTianMingAddition() {
			Hero hero = getHero();

			if(hero == null) {
				return new AttributeEmpty();
			}

			Additions add = hero.getAdditions();
			Attribute base = add.getBase();

			base = AttributeCalculator.adding(base,
					add.getEquipmentAddition(base));
			base = AttributeCalculator.adding(base, add.getYuanShenAddition());

			Attribute a = new AttributeEmpty();

			List<ExclusiveTemplet> ts = ExclusiveTempletConfig
					.findByFighterId(temp.getId());
			for (ExclusiveTemplet e : ts) {
				if(e.getJudge() == 0) {
					continue;
				}
				if (e.getExclusiveId().contains("" + hero.getTypeId())) {// 激活了
					a = AttributeCalculator.adding(a, getAddition(e, base));
				}
			}

			return a;
		}

		private Attribute getAddition(ExclusiveTemplet e, Attribute base) {

			AdditionType type = AdditionType.fromNum(e.getNatureType());

			float value = e.getNatureFixed() + type.get(base)
					* e.getNaturePar();

			AttributeSingle a = new AttributeSingle(type, (int) value);

			return a;
		}

	}

	/**
	 * 主动技能
	 *
	 * @author 林岑
	 *
	 */
	private static class ActiveSkill implements Skill {

		protected Skills dto;
		protected SkillTemplet temp;

		public ActiveSkill(Skills dto, SkillTemplet temp) {
			this.dto = dto;
			this.temp = temp;
		}

		@Override
		public int getId() {
			return dto.getSkillId();
		}

		@Override
		public int getLevel() {
			return dto.getLevel();
		}

		@Override
		public int getMaxLevel() {
			return D.MAX_SKILL_LEVEL;
		}

		// @Override
		// public Fraction getExp() {
		// return new Fraction(dto.getExp(), D.MAX_SKILL_EXP);
		// }

		@Override
		public boolean isEquipment() {
			String id = dto.getUname();
			City city = CityFactory.getCity(id);
			Team team = city.getTeam();
			Hero hero = team.get(dto.getFighterTypeId());
			return hero != null;
		}

		@Override
		public int getStep() {
			return temp.getStage();
		}

		@Override
		public int getFighterId() {
			return dto.getFighterTypeId();
		}

		@Override
		public int getPrice() {
			return (int) (temp.getSocial() + temp.getSocialGrow()
					* (getLevel() - 1));
		}

		@Override
		public Addition getDamage() {
			return new SkillDamageCalc().calc(this);
		}

		@Override
		public int getIdentification() {
			return dto.getIds();
		}

		@Override
		public int getShenJia() {
			return (int) (temp.getSocial() + (getLevel() - 1)
					* temp.getSocialGrow());
		}

		@Override
		public Skills getDto() {
			return dto;
		}

		@Override
		public Addition getDamageNext() {
			return new SkillDamageCalc().calcNext(this);
		}

	}

	public static List<Skill> create(Collection<Skills> ls) {
		List<Skill> a = Lists.newArrayList();
		for (Skills skills : ls) {
			a.add(create(skills));
		}
		return a;
	}

	private static Attribute extracted(int id, int lv, Attribute att) {

		SkillTemplet temp = SkillTempletConfig.get(id);

		AdditionType type = AdditionType.fromNum(temp.getPasvFront());

		if(type == null) {
			throw new NullPointerException("" + temp.getPasvFront() + "," + temp.getId());
		}

		float value = temp.getFrontFixed() + (lv - 1)
				* temp.getFrontGrowFixed();

		float percent = temp.getFrontPar() + (lv - 1)
				* temp.getFrontGrowPar();

		int attribute = type.get(att);

		int percentInt = (int) (percent * 100);
		
		percent = (percentInt + 0f) / 100;
		
		int v = (int) (attribute * percent + value);

		AttributeSingle a = new AttributeSingle(type, v);

		return a;
	}

	public static FighterSkillPro getBaseAddition(final int id) {
		FighterSkillPro.Builder b = FighterSkillPro.newBuilder();

		Addition calc = new SkillDamageCalc().calc(new IdLevel() {

			@Override
			public int getLevel() {
				return 1;
			}

			@Override
			public int getId() {
				return id;
			}
		});


		int v = getValue(calc);

		b.setDamage(v);
		b.setDamageNext(v);
		b.setFighterId(-1);
		b.setId(id);
		b.setPrice(-1);
		b.setSkillId(id);
		b.setSkillLevel(1);
		b.setFighterName("");
		return b.build();

	}

	private static int getValue(Addition damage) {
		return (int) (damage.isPercent() ? damage.getValue() * 100 : damage.getValue());
	}
}
