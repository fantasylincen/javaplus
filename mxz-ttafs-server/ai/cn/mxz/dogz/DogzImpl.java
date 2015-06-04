package cn.mxz.dogz;

import java.util.List;
import java.util.Map;

import cn.mxz.Attribute;
import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.buff.BufferManagerImpl;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.equipment.Equipment;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.BattleUnionSkill;
import cn.mxz.listeners.DogzStepUpNoticer;
import cn.mxz.script.Script;
import cn.mxz.team.Skill;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import db.dao.impl.DaoFactory;
import db.domain.UserDogz;
import define.D;

class DogzImpl extends EventDispatcher2Impl implements Dogz {

	private final class DogzSkillImplementation implements DogzSkill {
		@Override
		public float getProbablilty() {

			Float p = allProbabilitys.get(key());

			if (p == null) {

				p = 1f;

				
				allProbabilitys.put(key(), p);
			}

			return p;
		}

		@Override
		public int getId() {

			return getTemplet().getDogzSikll();
		}

		@Override
		public int getAddition() {

			Attribute t = getAdditionToGod();

			return t.getAttack() + t.getCrit() + t.getDefend() + t.getDodge() + t.getMAttack() + t.getMDefend() + t.getSpeed();
		}
	}



	private UserDogz					dogz;

	private boolean						activateSkill;

	private static Map<String, Float>	allProbabilitys	= Maps.newHashMap();

	private BufferManagerImpl			bufferManager;

	private int							angryValue;

	DogzImpl(UserDogz dogz) {

		this.dogz = dogz;

		addListener(new DogzStepUpNoticer());
	}



	@Override
	public Attribute getAdditionToGod() {
		return new AttributeEmpty();
	}

	@Override
	public boolean isFighting() {

		return dogz.getIsBattlefield();
	}

	@Override
	public int getLevel() {
		City city = CityFactory.getCity(dogz.getUname());
		return city.getDogzManager().getLevel();
	}

	@Override
	public int getTypeId() {

		return dogz.getDogzId();
	}


	@Override
	public void setFighting(boolean b) {

		dogz.setIsBattlefield(b);

		DaoFactory.getUserDogzDao().save(dogz);
	}

	@Override
	public DogzSkill getSkill() {

		return new DogzSkillImplementation();
	}

	private String key() {
		return dogz.getUname() + dogz.getDogzId();
	}

	@Override
	public void activateSkill() {

		activateSkill = true;

		Float p = allProbabilitys.get(key());

		if (p == null) {

			p = 1f;
		}

		// p -= 0.3f;//暂时不递减几率了

		allProbabilitys.put(key(), p);
	}

	@Override
	public void deactivateSkill() {

		activateSkill = false;
	}

	@Override
	public boolean isActivateSkill() {

		return activateSkill;
	}

	@Override
	public Attribute getAttribute() {
		
		AttributeEmpty a = new AttributeEmpty();
		DogzTemplet t = getTemplet();
		
		int an = t.getAttackN();
		int mn = t.getMAttackN();

		an = Script.Dogz.getDogzProperty(getLevel(), an);
		mn = Script.Dogz.getDogzProperty(getLevel(), mn);
		
		a.setAttack(an);
		a.setMAttack(mn);
		
		return a;
	}

	@Override
	public int getHpNow() {
		return getAttribute().getHp();
	}

	@Override
	public boolean isDeath() {
		return false;
	}

	@Override
	public BufferManager getBufferManager() {
		if (bufferManager == null) {
			bufferManager = new BufferManagerImpl();
		}
		return bufferManager;
	}

	@Override
	public void reduceHp(int reduce) {
	}

	@Override
	public SkillInBattle createSkill(Battle battle) {

		SkillInBattle skill;

		skill = new DogzSkillInBattle(this);

		skill.setAttacker(this);

		skill.setBattle(battle);

		if (skill.getId() != getTemplet().getCommonSkill()) {
			this.angryValue = 0;
		}

		return skill;
	}

	@Override
	public List<Skill> getSkills() {
		List<Skill> ls = Lists.newArrayList();
		return ls;
	}

	@Override
	public void addHp(int i) {
	}


	@Override
	public int getHpMax() {
		return getAttribute().getHp();
	}

	@Override
	public int getQuality() {
		return 1;
	}

	private DogzTemplet getTemplet() {
		return DogzTempletConfig.get(getTypeId());
	}

	@Override
	public int getStar() {
		return 1;
	}

	@Override
	public int getStarMax() {
		return 1;
	}

	@Override
	public List<Equipment> getEquipments() {
		return Lists.newArrayList();
	}

	@Override
	public boolean isAngry() {
		return angryValue >= D.DOGZ_ANGRY_VALUE;
	}

	@Override
	public void addAngry(int a) {
		this.angryValue += a;
	}

	@Override
	public int getAngryValue() {
		return angryValue;
	}

	@Override
	public Attribute getBaseAttribute() {
		return getAttribute();
	}

	@Override
	public void unangry() {
		angryValue = 0;
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

	@Override
	public int getLowerestDamage() {
		return 1;
	}

	@Override
	public BattleUnionSkill createUnionSkill(Battle battle) {
		return null;
	}

	@Override
	public void setAngry(int initAngry) {
		this.angryValue = initAngry;
	}



	@Override
	public int getStep() {
		DogzTemplet t = getTemplet();
		return t.getStepAgo();
	}



	@Override
	public String getName() {
		return getTemplet().getName();
	}



	@Override
	public UserDogz getDto() {
		return dogz;
	}

}
