package cn.mxz.fighter;

import java.util.Collection;
import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.AdditionMultiplier;
import cn.mxz.Attribute;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.ISkillTemplet;
import cn.mxz.QualityJobTemplet;
import cn.mxz.QualityJobTempletConfig;
import cn.mxz.QualityRatioTemplet;
import cn.mxz.QualityRatioTempletConfig;
import cn.mxz.QualityUpTempletConfig;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.base.TalentEmpty;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.buff.BufferManagerImpl;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.battle.skill.BattleSkill;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.script.Script;
import cn.mxz.skill.SkillFactory;
import cn.mxz.team.Skill;
import cn.mxz.util.debuger.Debuger;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import define.D;

public abstract class AbstractFighter extends EventDispatcher2Impl implements Fighter {

	public class SkillBean {

		private float addPro;
		private float skillPro;

		public SkillBean(float skillPro, float addPro) {
			this.skillPro = skillPro;
			this.addPro = addPro;
		}
		
		public float getSkillPro() {
			return skillPro;
		}
		
		public float getAddPro() {
			return addPro;
		}

	}

	private final class SkillFetcher implements Fetcher<SkillTemplet, SkillBean> {
		@Override
		public SkillBean get(SkillTemplet t) {
			
			return new SkillBean(t.getSkillPro() , t.getAddPro() );
		}
	}

	/**
	 * 过滤第一回合必然触发的技能
	 *
	 * @author 林岑
	 *
	 */
	private static class TrigerFirstRoundSkillFilter implements Predicate<Skill> {

		@Override
		public boolean apply(Skill input) {

			ISkillTemplet temp = SkillTempletConfig.get(input.getId());
			return temp.getIsTragerFirstRound() == 1;
		}

	}

	private static class ActiveSkillFilter implements Predicate<Skill> {

		@Override
		public boolean apply(Skill input) {
			ISkillTemplet temp = SkillTempletConfig.get(input.getId());
			return temp.getReleaseType() == 1;
		}

	}

	private static class TempWeightFetcher implements WeightFetcher<Skill> {

		@Override
		public Integer get(Skill t) {
			SkillTemplet temp = SkillTempletConfig.get(t.getId());
			return (int) (temp.getSkillPro() * 100000);
		}

	}

	private BufferManager	buff;

	// private float skillProbability;

	@Override
	public abstract void reduceHp(int reduce);

	@Override
	public abstract void addHp(int add);

	public AbstractFighter() {

		buff = new BufferManagerImpl();
	}

	@Override
	public boolean isDeath() {

		return getHpNow() <= 0;
	}

	@Override
	public final BufferManager getBufferManager() {
		return buff;
	}

	@Override
	public SkillInBattle createSkill(Battle battle) {

		if (getBufferManager().isNormalAttackOnly()) {
			return create(battle, SkillFactory.createNormalSkill(this));
		}

		if (battle.getRound() == 1) { // 第一回合触发辅助技能
			List<Skill> ls = getTrigerFirstSkill();
			if (!ls.isEmpty()) {
				Skill s = Util.Collection.getRandomOne(ls);
				return create(battle, s);
			}
		}

		return create(battle, randomSkill(battle));
	}

	/**
	 * 触发连携技能
	 *
	 * @param battle
	 *
	 * @return
	 */
	@Override
	public BattleUnionSkill createUnionSkill(Battle battle) {

		FighterTemplet temp = FighterTempletConfig.get(getTypeId());
		if (temp == null) {
			return null;
		}

		int a1 = temp.getExclusive10();
		BattleUnionSkill s;
		s = makeUnionSkill(a1, battle);
		if (s != null) {
			return s;
		}

		int a2 = temp.getExclusive11();
//		Debuger.debug("AbstractFighter.createUnionSkill()");
		s = makeUnionSkill(a2, battle);
		if (s != null) {
			return s;
		}
		return null;
	}

	private BattleUnionSkill makeUnionSkill(int a1, Battle battle) {
		SkillTemplet t = SkillTempletConfig.get(a1);
		if (t == null) {
			return null;
		}

		if (Util.Random.isHappen(t.getSkillPro())) {
			return createUnionSkill(t, battle);
		}

		return null;
	}

	private BattleUnionSkill createUnionSkill(SkillTemplet t, Battle battle) {
		String id = t.getExclusiveId();
		List<Integer> ids = Util.Collection.getIntegers(id);
		if (isOnFormation(ids, battle)) {
			return new BattleUnionSkill(t.getId(), ids, battle, this);
		}
		return null;
	}

	/**
	 * ids 这些人 是否都在阵上
	 *
	 * @param ids
	 * @param battle
	 * @return
	 */
	private boolean isOnFormation(List<Integer> ids, Battle battle) {
		for (int id : ids) {
			if (!isOnFormation(id, battle)) {
				return false;
			}
		}
		return true;
	}

	private boolean isOnFormation(int id, Battle battle) {
		BattleCamp b = battle.getFriends(this);
		List<Fighter> fighters = b.getFighters();
		for (Fighter fighter : fighters) {
			if (!fighter.isDeath() && fighter.getTypeId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 第一回合必然触发的技能
	 *
	 * @return
	 */
	private List<Skill> getTrigerFirstSkill() {
		Collection<Skill> all = Collections2.filter(getSkills(), new TrigerFirstRoundSkillFilter());
		return Lists.newArrayList(all);
	}

	private BattleSkill create(Battle battle, Skill s) {
		BattleSkill skill = new BattleSkill(s);

		skill.setAttacker(this);

		skill.setBattle(battle);
		return skill;
	}

	
	
	private Skill randomSkill(Battle battle) {
		List<Skill> skills = getSkills();
		skills = Lists.newArrayList(skills);
		skills = Lists.newArrayList(Collections2.filter(skills, new ActiveSkillFilter()));
		List<SkillTemplet> temps = Lists.newArrayList(getTemps(skills));
//		if((getTypeId() + "").startsWith("3")){
//			Debuger.debug("AbstractFighter.randomSkill()" + getTypeId() + " " + temps  + " " + skills);
//		}
		
//		if(Debuger.isDevelop()) {
//			return SkillFactory.createNormalSkill(this);
//		}
		
		if (!skills.isEmpty() && isSkillHappen(temps, battle)) {
			try {
				return Util.Random.getRandomOneByWeight(skills, new TempWeightFetcher());
			} catch (Exception e) {
				System.err.println("配置表有错:");
				e.printStackTrace();
				return skills.get(0);
			}
		}
		return SkillFactory.createNormalSkill(this);
	}
	
	
	/**
	 * 没触发技能的连续回合数
	 */
	private int roundLianXu = 0;
	
	protected boolean isSkillHappen(List<SkillTemplet> temps, Battle battle) {
//		Debuger.debug("AbstractFighter.isSkillHappen() --- ");
		SkillFetcher fetcher = new SkillFetcher();
		
		List<SkillBean> beans = Lists.newArrayList();
		for (SkillTemplet st : temps) {
			beans.add(fetcher.get(st));
		}
		
		float sum = 0;
		for (SkillBean b : beans) {
			sum += b.getSkillPro() + roundLianXu * b.getAddPro();
		}
		
		float pro = sum / beans.size();

//		Debuger.debug("AbstractFighter.isSkillHappen()" + getTypeId() + " -- " + pro);
		boolean happen = Util.Random.isHappen(pro);
		
		if(happen) {
			roundLianXu = 0;
		} else {
			roundLianXu++;
		}
		
		return happen;
	}

	private Collection<SkillTemplet> getTemps(final List<Skill> skills) {

		Predicate<? super SkillTemplet> a = new Predicate<SkillTemplet>() {

			@Override
			public boolean apply(SkillTemplet input) {
				for (Skill skill : skills) {
					if (skill.getId() == input.getId()) {
						return true;
					}
				}
				return false;
			}
		};

		return Collections2.filter(SkillTempletConfig.getAll(), a);
	}

	@Override
	public String toString() {
		return getTemplet().getName();
	}

	@Override
	public Attribute getBaseAttribute() {

		Attribute q = QualityUpTempletConfig.get(getLevel());

		q = AttributeCalculator.multiply(q, getCategoryAddition()); // 神将类别加成(主角,小怪,Boss...)

		q = AttributeCalculator.multiply(q, getProfessionAddition());// 职业加成:斗将

		// 仙师
		// 坚兵
		// 控士
		// 脉师

		q = AttributeCalculator.multiply(q, getStarAddition());// 神将品质加成

		float nanDuXiShu = getNanDuXiShu();

		q = AttributeCalculator.multiply(q, nanDuXiShu);

		Attribute templet = getTemplet();

		q = AttributeCalculator.adding(q, templet);

		return q;
	}

	@Override
	public Attribute getAttribute() {

		Attribute q = getBaseAttribute();

		q = AttributeCalculator.adding(q, getBufferManager().getAddition());

		return q;
	}

	protected abstract float getNanDuXiShu();

	/**
	 * 神将品质加成
	 *
	 * @return
	 */
	private float getStarAddition() {

		int quality = getQuality();

		GodQurlityTemplet godQurlityTemplet = GodQurlityTempletConfig.get(quality);

		if (godQurlityTemplet == null) {
			new NullPointerException("quality:" + quality + ", typeId:" + getTypeId()).printStackTrace();
			return 0;
		}

		return godQurlityTemplet.getMarkUp();
	}

	/**
	 * 职业加成:斗将 仙师 坚兵 控士 脉师
	 *
	 * @return
	 */
	private AdditionMultiplier getProfessionAddition() {

		int id = getTemplet().getProfessionId();

		QualityJobTemplet templet = QualityJobTempletConfig.get(id);

		return templet;
	}

	/**
	 * 神将类别加成(主角,小怪,Boss...)
	 *
	 * @return
	 */
	private AdditionMultiplier getCategoryAddition() {

		IFighterTemplet templet = getTemplet();

		if (templet == null) {

			throw new RuntimeException("找不到怪物模板!");
		}

		int category = templet.getCategory();

		QualityRatioTemplet c = QualityRatioTempletConfig.get(category);

		if (c == null) {

			return new TalentEmpty();
		}

		return c;
	}

	protected abstract IFighterTemplet getTemplet();

	@Override
	public final int getStar() {

		GodQurlityTemplet t = getQualityTemplet();

		int starCount = t.getStarCount();
		
		return starCount;
	}

	private GodQurlityTemplet getQualityTemplet() {

		int quality = getQuality();

		while (true) {

			GodQurlityTemplet t = GodQurlityTempletConfig.get(quality);

			if (t != null) {
				return t;
			}

			quality--;

			if (quality < 0) {
				return GodQurlityTempletConfig.getAll().get(0);
			}
		}

	}

	@Override
	public final int getStarMax() {

		GodQurlityTemplet t = getQualityTemplet();

		return t.getStarNumber();
	}

	@Override
	public int getQuality() {

		IFighterTemplet templet = getTemplet();

		return templet.getQuality();
	}

	@Override
	public int getLowerestDamage() {

		return (int) ((D.DEMON_DAMAGE_CONST + Script.ShangHai.getConst(getLowerestDamageLevel())) * getPar());
	}

	protected int getLowerestDamageLevel() {
		return getLevel();
	}

	protected double getPar() {
		return Util.Random.get(D.DEMON_DAMAGE_MIN, D.DEMON_DAMAGE_MAX);
	}
	

	@Override
	public int getStep() {
		int q = getQuality();
		GodQurlityTemplet t = GodQurlityTempletConfig.get(q);
		return t.getStep();
	}
	
	@Override
	public String getName() {
		String name = getTemplet().getName();
		return name;
	}
}
