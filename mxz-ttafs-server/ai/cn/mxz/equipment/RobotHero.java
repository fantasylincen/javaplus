package cn.mxz.equipment;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.math.Fraction;
import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.FighterConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.activity.boss.AdditionMultiplierImpl;
import cn.mxz.battle.Battle;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.YuanShen;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.battle.skill.BattleSkill;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.city.City;
import cn.mxz.city.EmptyGrid;
import cn.mxz.fighter.Additions;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.BattleUnionSkill;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.fighter.PlayerHeroImpl;
import cn.mxz.fighter.SkillTianMing;
import cn.mxz.fighter.SuperHeroImpl;
import cn.mxz.fighter.SuperPlayerImpl;
import cn.mxz.skill.SkillFactory;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.Skill;
import cn.mxz.team.builder.EquipmentTianMing;
import cn.mxz.team.builder.UnionSkill;
import cn.mxz.tianming.WuXingTianMing;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ITianFuSkill;
import cn.mxz.user.team.god.YuanShenManager;

import com.google.common.collect.Lists;

import db.domain.NewFighter;
import db.domain.NewFighterImpl;
import db.domain.Skills;

public class RobotHero implements Hero {

	private Hero		hero;
	private Attribute	attribute;
	private int			typeId;
	private String nick;
	private int shenJiaBase;

	public WuXingTianMing getWuXingTianMing() {
		return hero.getWuXingTianMing();
	}

	public boolean isPlayer() {
		return hero.isPlayer();
	}

	public List<EmptyGrid> getEmptyGrids() {
		return hero.getEmptyGrids();
	}

	public ITianFuSkill getTianFuSkill() {
		return hero.getTianFuSkill();
	}

	/**
	 * @param hero
	 * @param x 战斗力比例
	 * @param nick
	 * @param typeId
	 */
	public RobotHero(Hero hero, double x, String nick, int typeId) {

		this.nick = nick;
		
		this.typeId = typeId;
		
		if (hero.isPlayer()) {
			this.hero = new SuperPlayerImpl((PlayerHeroImpl) hero, new AdditionMultiplierImpl(x));
		} else {
			this.hero = new SuperHeroImpl((HeroImpl) hero, new AdditionMultiplierImpl(x));
		}

		attribute = this.hero.getAttribute();

		shenJiaBase = hero.getShenJiaBase() + Util.Random.get(-60, 60);
//		Debuger.debug("RobotHero.RobotHero() 伪造属性:" + hero.getName() + ":" + attribute);

	}

	public BattleUnionSkill createUnionSkill(Battle battle) {
		BattleUnionSkill s = hero.createUnionSkill(battle);
		if(s != null) {
			s.setAttacker(this);
		}
		return s;
	}

	/**
	 * @return
	 * @see cn.mxz.fighter.Fighter#getLowerestDamage()
	 */
	public int getLowerestDamage() {
		return hero.getLowerestDamage();
	}

	@Override
	public Attribute getAttribute() {
		return attribute;
	}

	@Override
	public int getTypeId() {
		return typeId;
	}

	@Override
	public int getShenJia() {
		int shenJiaBase = hero.getShenJiaBase();
		return shenJiaBase;
	}

	@Override
	public int getHpNow() {
		return hero.getHpNow();
	}

	@Override
	public boolean isDeath() {
		return hero.isDeath();
	}

	@Override
	public BufferManager getBufferManager() {
		return hero.getBufferManager();
	}

	@Override
	public void reduceHp(int reduce) {
		hero.reduceHp(reduce);
	}

	@Override
	public SkillInBattle createSkill(Battle battle) {

		BattleSkill skill = new BattleSkill(SkillFactory.createNormalSkill(this));

		skill.setAttacker(this);

		skill.setBattle(battle);
		return skill;
	}

	@Override
	public Fraction getExp() {
		return hero.getExp();
	}

	@Override
	public List<Skill> getSkills() {
		IFighterTemplet t = getTemplet();
		int skill = t.getSkill();
AbstractSkill ss = new AbstractSkill(skill) {
			
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
				return 0;
			}
			
			@Override
			public int getFighterId() {
				return 0;
			}
			
			@Override
			public Skills getDto() {
				return null;
			}
		};
		ArrayList<Skill> ls = Lists.newArrayList();
		ls.add(ss);
		return ls;
	}

	@Override
	public int getExpAll() {
//		return hero.getExpAll();
		return 0;
	}

	@Override
	public void addExp(int count) {
		hero.addExp(count);
	}

	@Override
	public void addHp(int i) {
		hero.addHp(i);
	}

	@Override
	public int getHpMax() {
		return hero.getHpMax();
	}


	@Override
	public int getQuality() {
		return hero.getQuality();
	}


	public void qualityLevelUp() {
		hero.qualityLevelUp();
	}

	@Override
	public int getLevel() {
		int i = hero.getLevel() - 1;
		if (i < 1) {
			i = 1;
		}
		return i;
	}

	@Override
	public int getStar() {
		return hero.getStar();
	}

	@Override
	public void addInheritCount() {
		hero.addInheritCount();
	}

	@Override
	public int getStarMax() {
		return hero.getStarMax();
	}

	@Override
	public int getInheritCount() {
		return hero.getInheritCount();
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public Attribute getBaseAttribute() {
		return attribute;
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

	@Override
	public int getMaxLevel() {
		return hero.getMaxLevel();
	}

	@Override
	public List<UnionSkill> getUnionSkills() {
		return Lists.newArrayList();
	}

	@Override
	public Additions getAdditions() {
		return hero.getAdditions();
	}
//
//	@Override
//	public int getReviseRemainSec() {
//		return hero.getReviseRemainSec();
//	}

	@Override
	public void commit() {
	}

	@Override
	public Fraction getFaLi() {
		return hero.getFaLi();
	}

	@Override
	public City getCity() {
		return hero.getCity();
	}

	@Override
	public YuanShenManager getYuanShenManager() {
		return new YuanShenManager() {

			@Override
			public List<YuanShen> getYuanShens() {
				return Lists.newArrayList();
			}

			@Override
			public void clear() {

			}

			@Override
			public void add(List<YuanShen> yuanShens) {
			}

			@Override
			public void set(List<YuanShen> yuanShens) {
			}
		};
	}

	@Override
	public HeroTianMing getTianMing() {
		return new HeroTianMing() {

			@Override
			public List<Integer> getTianMingIds() {
				return Lists.newArrayList();
			}

			@Override
			public Attribute getAddition() {
				return new AttributeEmpty();
			}
		};
	}

	@Override
	public List<Equipment> getZhuanShuEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public EquipmentTianMing getEquipmentTianMing() {
		return new EquipmentTianMing() {

			@Override
			public boolean isZhuanShu(Equipment e) {
				return false;
			}

			@Override
			public List<Equipment> getZSEquipments() {
				return Lists.newArrayList();
			}

			@Override
			public List<Integer> getTianMingIds() {
				return Lists.newArrayList();
			}
		};
	}

	@Override
	public NewFighter getDto() {
		NewFighter dto = new NewFighterImpl(hero.getDto());

		dto.setTypeId(typeId);

		return dto;
	}

	@Override
	public int get(HeroProperty property) {
		return hero.get(property);
	}

	@Override
	public String getName() {
		if(hero.isPlayer()) {
			return nick;
		}
		return hero.getName();
	}

	private IFighterTemplet getTemplet() {
		return FighterConfig.get(typeId);
	}

	@Override
	public SkillTianMing getSkillTianMing() {
		return hero.getSkillTianMing();
	}

	@Override
	public int getStep() {
		return hero.getStep();
	}
	
	@Override
	public String toString() {
		return hero.toString();
	}

	@Override
	public int getShenJiaBase() {
		return shenJiaBase;
	}
	
}
