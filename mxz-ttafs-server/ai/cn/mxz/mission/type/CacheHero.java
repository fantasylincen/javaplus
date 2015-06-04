package cn.mxz.mission.type;

import java.util.List;

import cn.javaplus.math.Fraction;
import cn.mxz.Attribute;
import cn.mxz.battle.Battle;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.city.City;
import cn.mxz.city.EmptyGrid;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.Additions;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.BattleUnionSkill;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.fighter.SkillTianMing;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.Skill;
import cn.mxz.team.builder.EquipmentTianMing;
import cn.mxz.team.builder.UnionSkill;
import cn.mxz.tianming.WuXingTianMing;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ITianFuSkill;
import cn.mxz.user.team.god.YuanShenManager;
import db.domain.NewFighter;

/**
 * 一个用于战斗的 缓存战士
 *
 * 一般的战士, 如果直接丢到战场上面去打仗, 会每次都去计算各项属性, 如果异常战斗100次攻击, 每次攻击可能要计算10次属性
 * 那么战斗系统的性能就会大大奖励, 而通过该方法创建的战士 会在创建的时候缓存下所有属性, 打仗的效率会高很多
 *
 * 同时这个战士会保留源战士的数据库dto , 可在打完仗后更新数据库
 *
 * @param hero
 * @return
 */
public class CacheHero implements Hero {

	public WuXingTianMing getWuXingTianMing() {
		return hero.getWuXingTianMing();
	}

	public List<EmptyGrid> getEmptyGrids() {
		return hero.getEmptyGrids();
	}

	public boolean isPlayer() {
		return hero.isPlayer();
	}

	public int getStep() {
		return hero.getStep();
	}

	@Override
	public int getShenJiaBase() {
		return hero.getShenJiaBase();
	}
	
	public BattleUnionSkill createUnionSkill(Battle battle) {
		return hero.createUnionSkill(battle);
	}

	public ITianFuSkill getTianFuSkill() {
		return hero.getTianFuSkill();
	}

	private Hero				hero;
	private Attribute			attribute;
	private List<Equipment>		zhuanShuEquipments;
	private EquipmentTianMing	equipmentTianMing;

	public CacheHero(Hero hero) {
		this.hero = hero;
		attribute = AttributeCalculator.copy(hero.getAttribute());
		zhuanShuEquipments = hero.getZhuanShuEquipments();
		equipmentTianMing = hero.getEquipmentTianMing();
	}

	@Override
	public Attribute getBaseAttribute() {
		return hero.getBaseAttribute();
	}

	@Override
	public Attribute getAttribute() {
		return attribute;
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
		SkillInBattle createSkill = hero.createSkill(battle);
		createSkill.setAttacker(this);
		return createSkill;
	}

	@Override
	public Fraction getExp() {
		return hero.getExp();
	}

	@Override
	public int getExpAll() {
		return hero.getExpAll();
	}

	@Override
	public List<Skill> getSkills() {
		return hero.getSkills();
	}

	@Override
	public void addExp(int count) {
		hero.addExp(count);
	}

	@Override
	public void addHp(int i) {
		hero.addHp(i);
	}

	/**
	 * @return
	 * @see cn.mxz.fighter.Fighter#getLowerestDamage()
	 */
	public int getLowerestDamage() {
		return hero.getLowerestDamage();
	}


	@Override
	public int getHpMax() {
		return hero.getHpMax();
	}


	public void qualityLevelUp() {
		hero.qualityLevelUp();
	}

	@Override
	public int getQuality() {
		return hero.getQuality();
	}

	@Override
	public int getTypeId() {
		return hero.getTypeId();
	}

	@Override
	public void addInheritCount() {
		hero.addInheritCount();
	}

	@Override
	public int getLevel() {
		return hero.getLevel();
	}

	@Override
	public int getInheritCount() {
		return hero.getInheritCount();
	}

	@Override
	public int getStar() {
		return hero.getStar();
	}

	@Override
	public int getStarMax() {
		return hero.getStarMax();
	}

	@Override
	public int getMaxLevel() {
		return hero.getMaxLevel();
	}

	@Override
	public List<Equipment> getEquipments() {
		return hero.getEquipments();
	}

	@Override
	public List<UnionSkill> getUnionSkills() {
		return hero.getUnionSkills();
	}

	@Override
	public Additions getAdditions() {
		return hero.getAdditions();
	}

//	@Override
//	public int getReviseRemainSec() {
//		return hero.getReviseRemainSec();
//	}

	@Override
	public void commit() {

		// Debuger.debug("HeroImpl.commit() " + this.getTemplet().getName() +
		// ":" + getHpNow() + "/" + getHpMax());
		hero.commit();
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
	public int getShenJia() {
		return hero.getShenJia();
	}

	@Override
	public HeroTianMing getTianMing() {
		return hero.getTianMing();
	}

	@Override
	public List<Equipment> getZhuanShuEquipments() {
		return zhuanShuEquipments;
	}

	@Override
	public EquipmentTianMing getEquipmentTianMing() {
		return equipmentTianMing;
	}

	@Override
	public YuanShenManager getYuanShenManager() {
		return hero.getYuanShenManager();
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (hero.equals(obj)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return hero.hashCode();
	}

	@Override
	public NewFighter getDto() {
		return hero.getDto();
	}

	@Override
	public int get(HeroProperty property) {
		return hero.get(property);
	}

	@Override
	public String getName() {
		return hero.getName();
	}

	@Override
	public int getNormalSkillId() {
		return hero.getNormalSkillId();
	}

	@Override
	public SkillTianMing getSkillTianMing() {
		return hero.getSkillTianMing();
	}
}
