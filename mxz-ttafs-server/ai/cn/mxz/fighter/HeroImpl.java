package cn.mxz.fighter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.math.Fraction;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.Attribute;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.FighterExpTemplet;
import cn.mxz.FighterExpTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.SuitTemplet;
import cn.mxz.SuitTempletConfig;
import cn.mxz.battle.YuanShen;
import cn.mxz.battle.buff.AttributeSingle;
import cn.mxz.city.City;
import cn.mxz.city.EmptyGrid;
import cn.mxz.dogz.Dogz;
import cn.mxz.dogz.DogzManager;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentManager;
import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.BeforeHeroExpAddEvent;
import cn.mxz.events.Events;
import cn.mxz.events.HeroLevelUpEvent;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import cn.mxz.events.fighter.QualityLevelUpEvent;
import cn.mxz.formation.AdditionType;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.skill.SkillManager;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.Skill;
import cn.mxz.team.builder.EquipmentComparator;
import cn.mxz.team.builder.EquipmentTianMing;
import cn.mxz.team.builder.UnionSkill;
import cn.mxz.tianming.WuXingTianMing;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ITianFuSkill;
import cn.mxz.user.team.god.YuanShenManager;
import cn.mxz.yuanshen.YuanShenImpl;
import cn.mxz.zan.ZanManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import db.dao.impl.DaoFactory;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;
import define.D;

/**
 * 神将
 * 
 * @author 林岑
 */
public class HeroImpl extends AbstractFighter implements Hero {

	private static final Events ET = Events.getInstance();

	private class YuanShenManagerImpl implements YuanShenManager {

		@Override
		public List<YuanShen> getYuanShens() {
			ArrayList<YuanShen> ls = Lists.newArrayList();
			for (int i = 0; i < NewFighterImpl.YUANSHEN_LEVEL_LEN
					&& i < NewFighterImpl.YUANSHEN_TYPE_LEN; i++) {
				YuanShenDto dto = new YuanShenDto(HeroImpl.this, i);
				if (!dto.isOpen() && dto.needOpen()) {
					dto.open();
				}
				if (dto.isOpen()) {
					ls.add(new YuanShenImpl(getCity(), dto));
				}
			}

			// Debuger.debug("HeroImpl.getYuanShens() 元神数量:" + ls.size() );
			return ls;
		}

		@Override
		public void clear() {
			for (int i = 0; i < NewFighterImpl.YUANSHEN_LEVEL_LEN
					&& i < NewFighterImpl.YUANSHEN_TYPE_LEN; i++) {
				getDto().setYuanshenType(i, -1);
				getDto().setYuanshenExp(i, 0);
				getDto().setYuanshenLevel(i, 1);
			}
			commit();
		}

		@Override
		public void add(List<YuanShen> yuanShens) {
			for (YuanShen yuanShen : yuanShens) {
				add(yuanShen);
			}
			commit();
		}

		private void add(YuanShen y) {
			int index = findFirstEmptyIndex();
			if (index != -1) {
				getDto().setYuanshenExp(index, y.getExp().getNumerator());
				getDto().setYuanshenLevel(index, y.getLevel());
				getDto().setYuanshenType(index, y.getType());
			}
		}

		/**
		 * 第一个空元神位
		 * 
		 * @return
		 */
		private int findFirstEmptyIndex() {
			for (int i = 0; i < NewFighterImpl.YUANSHEN_LEVEL_LEN
					&& i < NewFighterImpl.YUANSHEN_TYPE_LEN; i++) {
				YuanShenDto dto = new YuanShenDto(HeroImpl.this, i);
				if (!dto.isOpen()) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public void set(List<YuanShen> yuanShens) {
			for (int i = 0; i < NewFighterImpl.YUANSHEN_LEVEL_LEN
					&& i < yuanShens.size(); i++) {
				
				YuanShen y = yuanShens.get(i);
				getDto().setYuanshenExp(i, y.getExp().getNumerator());
				getDto().setYuanshenLevel(i, y.getLevel());
				getDto().setYuanshenType(i, y.getType());
			}
		}

	}

	private class EquipmentTianMingImpl implements EquipmentTianMing {

		@Override
		public List<Integer> getTianMingIds() {

			List<Equipment> es = getEquipments();

			List<Integer> ls = Lists.newArrayList();

			int typeId = HeroImpl.this.getTypeId();
			for (Equipment equipment : es) {
				int equipTypeId = equipment.getTypeId();

				Integer tiamMingId = EquipmentTianMingConfig.getId(typeId,
						equipTypeId);
				if (tiamMingId != null) {
					ls.add(tiamMingId);
				}
			}

			return ls;
		}

		@Override
		public List<Equipment> getZSEquipments() {
			List<Equipment> all = getEquipments();
			Predicate<Equipment> predicate = new Predicate<Equipment>() {

				@Override
				public boolean apply(Equipment e) {
					return isZhuanShu(e);
				}
			};

			return Lists.newArrayList(Collections2.filter(all, predicate));
		}

		@Override
		public boolean isZhuanShu(Equipment e) {

			int eTypeId = e.getTypeId();
			int tId = HeroImpl.this.getTypeId();

			Integer tiamMingId = EquipmentTianMingConfig.getId(tId, eTypeId);

			return tiamMingId != null;
		}

	}

	private class HeroAdditions implements Additions {

		@Override
		public Attribute getDogzAddition() {

			DogzManager manager = city.getDogzManager();

			Dogz onFighting = manager.getFighting();

			if (onFighting != null) {

				Attribute a = onFighting.getAdditionToGod();

				return a;
			}

			return new AttributeEmpty();
		}

		@Override
		public Attribute getDogzSkillAddition() {

			return new AttributeEmpty();
		}

		@Override
		public Attribute getEquipmentAddition(Attribute currentHeroAttribute) {

			EquipmentManager mg = city.getEquipmentManager();

			Map<Integer, ? extends Equipment> es = mg
					.getEquipmentAll(HeroImpl.this);

			Attribute a = new AttributeEmpty();
			for (Equipment e : es.values()) {

				Attribute addition = e.getBase();

				a = add(addition, a);
			}

			return a;
		}

		@Override
		public AttributeSetable getAll() {

			Attribute add = getBase();

			add = add(add, getEquipmentAddition());

			add = add(add, getDogzAddition()); // 神兽加成
			add = add(add, getTaoZhuangAddition()); // 套装加成
			add = add(add, getDogzSkillAddition());
			add = add(add, getFormationAddition());// 阵形
			add = add(add, getZanAddition());// 每日签到加成
			add = add(add, getYuanShenAddition());// 元神
			add = add(add, getSkillAddition());// 技能

			add = add(add, getSkillTianMing().getAddition());// 技能天命

			add = add(add, getTianMingAddition());

			// 装备天命 30%
			// 技能天命 15%

			add = add(add, getEquipmentFuJia());// 装备附加属性

			AttributeSetable cp = AttributeCalculator.copy(add);

			for (AdditionType a : AdditionType.values()) {
				int value = a.get(cp);
				if (value < 0) {
					a.set(cp, 0);
				}
			}
			return cp;
		}

		private Attribute getEquipmentAddition() {
			EquipmentManager mg = city.getEquipmentManager();

			Map<Integer, ? extends Equipment> es = mg
					.getEquipmentAll(HeroImpl.this);

			Attribute a = new AttributeEmpty();
			for (Equipment e : es.values()) {

				Attribute addition = e.getAddition();

				a = add(addition, a);
			}

			return a;
		}

		private Attribute getEquipmentFuJia() {

			Attribute a = new AttributeEmpty();

			List<Equipment> all = getEquipments();

			Attribute base2 = getBase();

			for (Equipment equipment : all) {

				EquipmentTemplet t = equipment.getTemplet();

				// 附加属性1
				Attribute b = getFuJia(t.getAdditionType2(),
						t.getAdditionValue2(), t.getAdditionPercent2(), base2);

				// 附加属性2
				Attribute c = getFuJia(t.getAdditionType3(),
						t.getAdditionValue3(), t.getAdditionPercent3(), base2);

				a = AttributeCalculator.adding(a, b, c);
			}
			return a;
		}

		private Attribute getFuJia(int type, float value, float percent,
				Attribute base) {

			AdditionType at = AdditionType.fromNum(type);

			if (at == null) {
				return new AttributeEmpty();
			}

			int i = at.get(base);

			value += i * percent;

			return new AttributeSingle(at, (int) value);
		}

		private Attribute getTaoZhuangAddition() {
			Map<Integer, Integer> suite = Maps.newHashMap();// 套装id，数量
			List<Equipment> equipments = getEquipments();

			for (Equipment equipment : equipments) {
				EquipmentTemplet templet = EquipmentTempletConfig.get(equipment
						.getTypeId());
				if (templet.getSuitId() != -1) {
					Integer count = suite.get(templet.getSuitId());
					if (count == null) {
						count = 0;
					}
					count++;
					suite.put(templet.getSuitId(), count);
				}
			}
			Attribute ret = new AttributeEmpty();
			for (Entry<Integer, Integer> s : suite.entrySet()) {
				for (SuitTemplet t : getSuiteTemplet(s.getKey(), s.getValue())) {
					AdditionType at = AdditionType.fromNum(t
							.getBaseAdditionType());
					Attribute addition = new AttributeSingle(at,
							t.getAdditionValue());
					ret = add(ret, addition);
				}
			}

			return ret;

		}

		private List<SuitTemplet> getSuiteTemplet(int suiteId, int count) {
			List<SuitTemplet> list = Lists.newArrayList();
			for (SuitTemplet t : SuitTempletConfig.getAll()) {
				if (t.getSuitId() == suiteId && t.getRow() <= count) {
					list.add(t);
				}
			}
			return list;

		}

		private Attribute getZanAddition() {
			ZanManager zanManager = city.getZanManager();
			Attribute addition = zanManager.getAddition(HeroImpl.this);
			return addition;
		}

		@Override
		public Attribute getTianMingAddition() {
			return getTianMing().getAddition();
		}

		private Attribute getSkillAddition() {

			List<Skill> skills = getSkills();

			Attribute a = new AttributeEmpty();

			for (Skill skill : skills) {

				if (skill instanceof PassiveSkill) {

					PassiveSkill s = (PassiveSkill) skill;

					Attribute addition = s.getAddition();

					a = add(a, addition);
				}
			}

			return a;
		}

		@Override
		public Attribute getYuanShenAddition() {
			List<YuanShen> yuanShens = getYuanShenManager().getYuanShens();
			Attribute add = new AttributeEmpty();
			for (YuanShen yuanShen : yuanShens) {
				add = add(add, yuanShen.getAddition());
			}
			return add;
		}

		private Attribute getFormationAddition() {
			Formation formation = city.getFormation();
			Attribute addition = formation.getAddition(HeroImpl.this);
			return addition;
		}

		@Override
		public Attribute getBase() {
			Attribute a = HeroImpl.super.getAttribute();
			a = add(getShengXingJiaCheng(), a);
			return a;
		}

		private Attribute getShengXingJiaCheng() {
			IFighterTemplet temp = getTemplet();
			int star = getStar();

			Attribute multiply = AttributeCalculator.multiply(temp,
					D.SHENG_XING_JIA_CHENG_PAR * (star));

			return multiply;
		}

		// * 基础加成
		// * 人物（等级+进阶），装备主属性，装备套装属性，元神

		@Override
		public Attribute getBase2() {
			Attribute bs = getBase();
			Attribute eq = getEquipmentAddition(bs);
			Attribute tz = getTaoZhuangAddition();
			Attribute ys = getYuanShenAddition();
			return add(bs, eq, tz, ys);
		}
	}

	private class HeroTianMingImpl implements HeroTianMing {

		public HeroTianMingImpl() {
		}

		@Override
		public List<Integer> getTianMingIds() {

			List<ExclusiveTemplet> tm = ExclusiveTempletConfig
					.findByFighterId(HeroImpl.this.getTypeId());

			List<Integer> is = Lists.newArrayList();

			// Debuger.debug("HeroImpl.HeroTianMingImpl.getTianMingIds()" +
			// getName());

			for (ExclusiveTemplet e : tm) {
				if (!Sets.newHashSet(1, 2, 3).contains(e.getType())) {
					continue;
				}
				if (e.getJudge() == 0) {
					continue;
				}
				if (isActive(e)) {
					is.add(e.getId());
				}
			}

			// Debuger.debug("HeroImpl.HeroTianMingImpl.getTianMingIds()" +
			// getName() + ":" + is);
			return is;
		}

		/**
		 * 改天命是否被激活
		 * 
		 * @param e
		 * @return
		 */
		private boolean isActive(ExclusiveTemplet e) {

			PlayerCamp selected = city.getFormation().getSelected();

			if (!contains()) {
				return false;
			}

			List<Hero> fighters = selected.getFighters();
			List<Hero> ls = Lists.newArrayList(fighters);
			ls.addAll(city.getFormation().getAlternate().getFighters());

			List<Integer> ids = Collection.getIntegers(e.getExclusiveId());

			for (Hero hero : ls) {

				ids.remove(new Integer(hero.getTypeId()));

			}

			return ids.isEmpty();
		}

		private boolean contains() {
			PlayerCamp selected = city.getFormation().getSelected();

			List<Hero> fighters = selected.getFighters();

			fighters.addAll(city.getFormation().getAlternate().getFighters());

			for (Hero hero : fighters) {
				if (hero.getTypeId() == getTypeId()) {
					return true;
				}
			}

			return false;

		}

		// 天命属性=(人物本身属性+装备固定属性+元神属性)*天命表中加成系数
		@Override
		public Attribute getAddition() {

			List<Integer> ids = getTianMingIds();

			Additions additions = getAdditions();

			Attribute base = additions.getBase2();

			Attribute eqa = additions.getEquipmentAddition(base);

			base = add(base, eqa);

			Attribute ysa = additions.getYuanShenAddition();

			base = add(base, ysa);

			Attribute add = new AttributeEmpty();

			for (Integer id : ids) {

				ExclusiveTemplet temp = ExclusiveTempletConfig.get(id);

				if (temp.getJudge() == 0) {
					continue;
				}
				TianMingAddition tmAdd = new TianMingAddition(base, temp);

				add = add(add, tmAdd);
			}

			return add;
		}

	}

	private NewFighter dto;

	protected City city;

	public HeroImpl(Hero hero) {

		this.dto = new NewFighterImpl(hero.getDto());

		city = hero.getCity();

		ensureHpLowerMax();
	}

	public HeroImpl() {

	}

	private Attribute add(Attribute... a) {
		return AttributeCalculator.adding(a);
	}

	@Override
	public SkillTianMing getSkillTianMing() {
		return new SkillTianMing(HeroImpl.this);
	}

	@Override
	public int getTypeId() {
		return getDto().getTypeId();
	}

	@Override
	public Attribute getAttribute() {

		Attribute all = getAdditions().getAll();

		return all;
	}

	@Override
	public Additions getAdditions() {

		return new HeroAdditions();
	}

	@Override
	public NewFighter getDto() {
		return dto;
	}

	@Override
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public void commit() {
		NewFighter dto2 = getDto();

		// Debuger.debug("HeroImpl.commit() 经验:" + dto2.getExp());

		DaoFactory.getNewFighterDao().update(dto2);
	}

	@Override
	public Fraction getExp() {

		final FighterTemplet temp = FighterTempletConfig.get(getTypeId());

		final int n = getDto().getExp();

		int d = 1;

		int level = getLevel();

		final FighterExpTemplet ft = FighterExpTempletConfig.get(level);

		Util.Exception.checkNull(ft, level);

		float expPar = temp.getExpPar();

		int player = ft.getPlayer();

		d = (int) (player * expPar);

		return new Fraction(n, d);
	}

	@Override
	public int getExpAll() {

		int level = getLevel();

		final FighterExpTemplet ft = FighterExpTempletConfig.get(level);

		int typeId = dto.getTypeId();
		FighterTemplet temp = FighterTempletConfig.get(typeId);

		Util.Exception.checkNull(temp, typeId);

		Fraction exp = getExp();

		int i = (int) (exp.getNumerator() + ft.getPlayerSum()
				* temp.getExpPar());

		return i;

	}

	@Override
	public int getLevel() {
		int level = getDto().getLevel();
		if (level > D.USER_MAX_LEVEL) {
			level = D.USER_MAX_LEVEL;
		}
		return level;
	}

	@Override
	public void addExp(int count) {

		ET.dispatch(new BeforeHeroExpAddEvent(this, city, getLevel()));

		int oldShenJia = getCity().getFormation().getShenJia();

		if (addExpWithOutEvent(count)) {
			ET.dispatch(new HeroLevelUpEvent(this, city, getLevel()));

			if (isPlayer()) {
				ET.dispatch(new PlayerHeroLevelUpEvent(this, city, getLevel()));
			}

			Events.getInstance().dispatch(
					new AttributeChangeEvent(getCity(), oldShenJia));
		}

		commit();
	}

	/**
	 * 增加经验, 不会提交到数据库, 不会增加派发事件
	 * 
	 * @param count
	 * @return
	 */
	public boolean addExpWithOutEvent(int count) {

		int maxLevel = getMaxLevel();
		if (getLevel() >= maxLevel) {
			return false;
		}

		boolean isLevelUp = false;
		getDto().addExp(count);

		while (!getExp().isProper()) { // 如果经验是个假分数
			getDto().addExp(-getExp().getDenominator()); // 当前经验的分子 -去当前经验的分母
			getDto().addLevel(1); // 等级 + 1
			isLevelUp = true;
		}
		return isLevelUp;
	}

	@Override
	public List<Skill> getSkills() {

		SkillManager m = city.getSkillManager();

		List<Skill> skills = m.getSkills(this);

		return skills;
	}

	@Override
	public int getQuality() {

		int q = get(HeroProperty.QUALITY);

		if (q == 0) {
			IFighterTemplet temp = FighterTempletConfig.get(getTypeId());
			int quality = temp.getQuality();

			set(HeroProperty.QUALITY, quality);

			commit();

			return quality;
		}

		return q;
	}

	public void setDto(NewFighter dto) {
		this.dto = dto;
	}

	@Override
	public void reduceHp(int reduce) {

		ensureHpLowerMax(); // 保证玩家气血在最大气血之下

		// Debuger.debug("HeroImpl.reduceHp()" + this + " reduce = " + reduce);

		addHp(-reduce);
	}

	@Override
	public void addHp(int add) {

		add(HeroProperty.HP, add);

		// Debuger.debug("HeroImpl.addHp()" + this + ":" + add);
		// new Exception().printStackTrace();

		ensureHpLowerMax();
	}

	/**
	 * 保证玩家气血在最大气血之下
	 */
	protected void ensureHpLowerMax() {

		int hpMax = getHpMax();

		if (get(HeroProperty.HP) > hpMax) {

			set(HeroProperty.HP, hpMax);
		}
	}

	@Override
	public int getHpMax() {

		Attribute attribute = getAttribute();

		return attribute.getHp();
	}

	private void set(HeroProperty v, int value) {
		getDto().setV(v.getValue(), value);
	}

	@Override
	public int getMaxLevel() {

		GodQurlityTemplet godQurlityTemplet = GodQurlityTempletConfig
				.get(getQuality());

		return godQurlityTemplet.getMaxLevel();
	}

	@Override
	public List<UnionSkill> getUnionSkills() {

		List<UnionSkill> us = new ArrayList<UnionSkill>();

		FighterTemplet temp = (FighterTemplet) getTemplet();

		int teamWork1 = temp.getExclusive10();

		int teamWork2 = temp.getExclusive11();

		if (isActivate(teamWork1)) {

			us.add(build(teamWork1));
		}

		if (isActivate(teamWork2)) {

			us.add(build(teamWork2));
		}

		return us;
	}

	private UnionSkill build(int t) {

		return new UnionSkillImpl(t);
	}

	/**
	 * 判断这个技能是否被激活
	 * 
	 * @param skillId
	 * @return
	 */
	private boolean isActivate(int skillId) {

		return false;

	}

	@Override
	public int getHpNow() {

		ensureHpLowerMax();

		int hp = get(HeroProperty.HP);

		return hp;
	}

	@Override
	public int get(HeroProperty property) {
		return getDto().getV(property.getValue());
	}

	// @Override
	// public int getReviseRemainSec() {
	// return 0;
	// }

	@Override
	protected float getNanDuXiShu() {
		return 1f;
	}

	@Override
	public void addInheritCount() {
		add(HeroProperty.INHERIT_COUNT, 1);
		commit();
	}

	private void add(HeroProperty p, int add) {
		int value = p.getValue();
		int oldValue = getDto().getV(value);
		int newValue = oldValue + add;
		getDto().setV(value, newValue);
	}

	@Override
	public int getInheritCount() {
		return get(HeroProperty.INHERIT_COUNT);
	}

	@Override
	public Fraction getFaLi() {
		List<Skill> all = getSkills();

		int n = 0;
		for (Skill skill : all) {
			FighterTemplet templet = (FighterTemplet) getTemplet();
			if (templet.getSkill() != skill.getId()) { // 非天赋技能
				SkillTemplet temp = SkillTempletConfig.get(skill.getId());
				int migc = temp.getMigc();
				n += migc;
			}
		}

		int d = getBaseAttribute().getMagic();

		return new Fraction(n, d);
	}

	@Override
	public List<Equipment> getEquipments() {
		EquipmentManager em = city.getEquipmentManager();
		ArrayList<Equipment> ls = Lists.newArrayList();
		ls.addAll(em.getEquipmentAll(this).values());
		Comparator<Equipment> c = new EquipmentComparator();
		Collections.sort(ls, c);
		return ls;
	}

	@Override
	public int getShenJia() {

		// 单人身价=人物本身+装备+技能+元神
		int ret = getShenJiaBase(); // 基础身价
		ret += getEquipmentShenJia(); // 装备身价
		ret += getSkillShenJia(); // 技能身价
		ret += getYuanShenShenJia(); // 元神身价
		ret += getShenJiaGrowth();

		return ret;
	}

	private int getShenJiaGrowth() {
		int socialSX = getTemplet().getSocialSX();
		int q = getStar();
		int i = socialSX * (q - 1);
		i = Math.max(0, i);
		return i;
	}

	private int getYuanShenShenJia() {
		int ret = 0;
		List<YuanShen> all = getYuanShenManager().getYuanShens();
		for (YuanShen yuanShen : all) {
			ret += yuanShen.getShenJia();
		}
		return ret;
	}

	private int getSkillShenJia() {
		int ret = 0;
		List<Skill> skills = getSkills();
		for (Skill skill : skills) {
			ret += skill.getShenJia();
		}
		return ret;
	}

	private int getEquipmentShenJia() {
		int ret = 0;
		List<Equipment> all = getEquipments();
		for (Equipment equipment : all) {
			ret += equipment.getShenJia();
		}
		return ret;
	}

	public int getShenJiaBase() {
		FighterTemplet temp = (FighterTemplet) getTemplet();
		float f = (getLevel() - 1) * temp.getSocialGrow();

		int ret = (int) (temp.getSocial() + f);
		return ret;
	}

	@Override
	public HeroTianMing getTianMing() {
		return new HeroTianMingImpl();
	}

	@Override
	public List<Equipment> getZhuanShuEquipments() {
		EquipmentTianMing tm = getEquipmentTianMing();
		return tm.getZSEquipments();
	}

	@Override
	public EquipmentTianMing getEquipmentTianMing() {
		return new EquipmentTianMingImpl();
	}

	@Override
	public YuanShenManager getYuanShenManager() {
		return new YuanShenManagerImpl();
	}

	@Override
	public String getName() {
		return getTemplet().getName();
	}

	@Override
	protected FighterTemplet getTemplet() {
		return FighterTempletConfig.get(getTypeId());
	}

	@Override
	public int getNormalSkillId() {
		return getTemplet().getCommonSkill();
	}

	@Override
	public ITianFuSkill getTianFuSkill() {

		List<Skill> skills = city.getSkillManager().getSkills(this);
		for (Skill skill : skills) {
			IFighterTemplet t = getTemplet();
			if (t.getSkill() == skill.getId()) {
				return new TianFuSkillImpl(skill);
			}
		}
		throw new RuntimeException("没有找到天赋技能");
	}

	@Override
	public boolean isPlayer() {
		return getTemplet().getCategory() == 3;
	}

	@Override
	public List<EmptyGrid> getEmptyGrids() {
		List<Equipment> all = getEquipments();

		Set<Part> parts = Sets.newHashSet(Part.values());

		// 移除所有的已有装备部位
		for (Equipment e : all) {
			Part part = e.getPart();
			parts.remove(part);
		}

		return buildEmptyGrids(parts);
	}

	private List<EmptyGrid> buildEmptyGrids(Set<Part> parts) {
		List<EmptyGrid> ls = Lists.newArrayList();
		for (Part part : parts) {
			ls.add(new EmptyGrid(part));
		}
		return ls;
	}

	@Override
	public void qualityLevelUp() {

		int oldShenJia = getCity().getFormation().getShenJia();

		int quality = HeroImpl.this.getQuality();

		GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);

		set(HeroProperty.QUALITY, temp.getQualityIdAfterLevelUp());

		Events.getInstance().dispatch(
				new AttributeChangeEvent(getCity(), oldShenJia));
		Events.getInstance().dispatch(new QualityLevelUpEvent(getCity(), this));
		commit();
	}

	@Override
	public WuXingTianMing getWuXingTianMing() {
		return new WuXingTianMing(HeroImpl.this.getTypeId(),
				HeroImpl.this.getStar());
	}
}