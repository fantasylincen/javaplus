package cn.mxz.battle.skill.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.BuffTempletConfig;
import cn.mxz.DogzTempletConfig;
import cn.mxz.FighterConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.ISkillTemplet;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.buff.BufferImpl;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.battle.skill.attacktype.AttackType;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.script.Script;
import cn.mxz.skill.SkillDamageCalc;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.util.Factory;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import define.D;

/**
 * 
 * 技能
 * 
 * @author 林岑
 * 
 */
public abstract class AbstractSkill implements SkillInBattle {

	/**
	 * 施法方
	 */
	protected Fighter attacker;

	/**
	 * 战场
	 */
	protected Battle battle;

	private int id;

	public AbstractSkill(int id) {
		this.id = id;
	}

	@Override
	public List<FighterBeAttack> fire() {
		AttackType at = (AttackType) Factory.get("attackType"
				+ getTemplet().getAttackType());

		if (getTargetCamp(at).isAllDeath()) {
			return Lists.newArrayList();
		}

		return attack(at);
	}

	protected int getDamage(Fighter f) {

		double k = Random.get(0.95, 1.05);

		int damage = getAttack();

		float x = getDamage().getValue();

		damage *= (x / 100 + D.SKILL_DAMEAGE_PAR);

		damage *= k;

		double defend = getDefend(f);

		damage -= defend;

		damage *= D.ZONG_SHANG_HAI_XI_SHU;
				
		double max = Math.max(getHarmFixed(), damage);

		return (int) max;
	}

	/**
	 * a 克制 f ?
	 * 
	 * @param a
	 * @param f
	 * @return
	 */
	private boolean isXiangKe(Fighter a, Fighter f) {

		HashMap<Integer, Integer> map = Maps.newHashMap();
		map.put(1, 2);
		map.put(2, 5);
		map.put(3, 4);
		map.put(4, 1);
		map.put(5, 3);

		FighterTemplet ft1 = FighterTempletConfig.get(a.getTypeId());
		FighterTemplet ft2 = FighterTempletConfig.get(f.getTypeId());

		Integer aa = map.get(ft1.getAttribute());

		if (aa == null) {
			return false;
		}

		return aa == ft2.getAttribute();
	}

	@Override
	public Addition getDamage() {
		return new SkillDamageCalc().calc(this);
	}

	@Override
	public Addition getDamageNext() {
		return new SkillDamageCalc().calcNext(this);
	}

	protected int getDefend(Fighter f) {
		SkillTemplet temp = getTemplet();
		if (temp.getDamageType() == 2) {
			return f.getAttribute().getMDefend();
		} else {
			return f.getAttribute().getDefend();
		}
	}

	/**
	 * 固定伤害
	 * 
	 * @return
	 */
	private double getHarmFixed() {
		ISkillTemplet temp = getTemplet();
		return temp.getHarmFixed() + temp.getHarmGrowFixed() * (getLevel() - 1);
	}

	protected int getAttack() {

		SkillTemplet temp = SkillTempletConfig.get(id);

		int speedAddition = getSpeedAddition();

		int attack = 0;

		attack += (int) (attacker.getAttribute().getAttack() * temp
				.getNormalRatio());
		attack += (int) (attacker.getAttribute().getMAttack() * temp
				.getMagicRatio());
		attack += speedAddition;

		return attack;
	}

	private int getSpeedAddition() {

		SkillTemplet temp = SkillTempletConfig.get(id);

		int typeId = attacker.getTypeId();
		
		FighterTemplet t = FighterTempletConfig.get(typeId);
		
		double aa = D.BATTLE_SPEED_ADDITION;
		if(t != null) {
			
			int vocation = t.getVocation();
			
			if(vocation == 1) {
				aa = D.BATTLE_SPEED_ADDITION1;
			} else if (vocation == 2) {

				aa = D.BATTLE_SPEED_ADDITION2;
			} else if (vocation == 3) {

				aa = D.BATTLE_SPEED_ADDITION3;
			} else if (vocation == 4) {

				aa = D.BATTLE_SPEED_ADDITION4;
			} else {

				aa = D.BATTLE_SPEED_ADDITION5;
			}
		}
		
		return (int) (attacker.getAttribute().getSpeed() * temp.getSpeedRatio() * aa);
	}

	/**
	 * 攻击者位置
	 * 
	 * @return
	 */
	private final int getAttackerPosition() {

		final PlayerCamp under = battle.getUnderPlayerCamp();

		Fighter fighter = getFighter();

		final boolean isUnder = under.getFighters().contains(fighter);

		int position;

		if (isUnder) {

			position = under.getPosition(fighter);

		} else {

			position = battle.getUpper().getPosition(fighter);
		}

		if (position == -1) {

			throw new RuntimeException(" 位置错误!" + isUnder);
		}

		return position;
	}

	protected SkillTemplet getTemplet() {
		int id2 = getId();
		return SkillTempletConfig.get(id2);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Fighter getFighter() {
		return attacker;
	}

	@Override
	public void setAttacker(Fighter fighter) {
		this.attacker = fighter;
	}

	@Override
	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	/**
	 * 敌方阵营
	 * 
	 * @return
	 */
	private BattleCamp getEnemy() {
		Util.Exception.checkNull(battle, "" + attacker);
		return battle.getEnemy(attacker);
	}

	/**
	 * 友方阵营
	 * 
	 * @return
	 */
	private BattleCamp getFriends() {
		return battle.getFriends(attacker);
	}

	@Override
	public final int getMaxLevel() {

		return D.MAX_SKILL_LEVEL;
	}

	/**
	 * 进行一次攻击
	 * 
	 * @param at
	 * @return
	 */
	protected List<FighterBeAttack> attack(AttackType at) {

		final BattleCamp camp = getTargetCamp(at);

		if (camp.isAllDeath()) {

			return new ArrayList<FighterBeAttack>();
		}

		List<FighterBeAttack> all = at.getFighters(camp, getAttackerPosition());

		all = new ArrayList<FighterBeAttack>(all);

		Iterator<FighterBeAttack> it = all.iterator();

		boolean isFirst = true;

		while (it.hasNext()) {

			FighterBeAttack f = it.next();

			final Fighter target = f.getTarget();

			if (target.isDeath()) { // 如果死了, 就不要打他

				it.remove();

				continue;
			}

			createBuff(target);

			int damage = getDamage(target);

			if (isFirst && isBitBonus()) {
				damage = (int) (damage * D.SKILL_DAMAGE_SCALE);
			}

			damage = xxx(damage, f);

			damage = wuXing(damage, f);


			int d1 = attacker.getLowerestDamage(); // 最低伤害

			if (damage < d1) {
				damage = d1;
			}
			
//			if(Debuger.isDevelop()) {
//				damage = 300000;
//			}

			update(at, f, target, damage);

			isFirst = false;
		}

		if (all.isEmpty()) {

			throw new RuntimeException("被攻击战士列表为空!" + at);
		}

		return all;
	}

	private int wuXing(int damage, FighterBeAttack f) {
		Fighter defender = f.getTarget();
		// GOGY_BY_RESTRICTION 0.9
		// GOGY_NO_RESTRICTION 1
		// GOGY_RESTRICTION 1.2

		if (isXiangKe(attacker, defender)) {
			return (int) (damage * D.GOGY_RESTRICTION);
		} else if (isXiangKe(defender, attacker)) {
			return (int) (damage * D.GOGY_BY_RESTRICTION);
		} else {
			return (int) (damage * D.GOGY_NO_RESTRICTION);
		}
	}

	private boolean isBitBonus() {
		return getTemplet().getBitBonus() == 1;
	}

	protected int xxx(int damage, FighterBeAttack f) {

		if (isPlayer()) {
			int level = attacker.getLevel();
			if (level > D.MIN_DODGE_LEVEL) {
				if (isDodge(f)) {
					f.setHit(false);
					return 0;
				}
			}
		}

		if (isCrit(f)) {
			f.setCrit(true);
			damage *= (D.CRIT_ADDITION
					+ attacker.getAttribute().getCritAddition() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI);
		}

		if (isBlock(f)) {
			f.setBlock(true);
			damage *= D.BLOCK_SCALE;
		}

		return damage;
	}

	private boolean isPlayer() {
		int t = attacker.getTypeId();
		IFighterTemplet temp = FighterConfig.get(t);
		if (temp == null) {
			throw new RuntimeException("" + t);
		}
		int c = temp.getCategory();
		return c == 3;
	}

	private boolean isBlock(FighterBeAttack f) {
//		if(Debuger.isDevelop()) {
//			return true;
//		}
		double defenderBlock = f.getTarget().getAttribute().getBlock() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double attackerRBlock = attacker.getAttribute().getRBlock() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double result = defenderBlock - attackerRBlock;

		if (result < D.LOW_BLOCK_PROBABILITY) {
			result = D.LOW_BLOCK_PROBABILITY;
		}

		if (result > D.HIGH_BLOCK_PROBABILITY) {
			result = D.HIGH_BLOCK_PROBABILITY;
		}

		return Random.isHappen((float) result);
	}

	private boolean isCrit(FighterBeAttack f) {
		double defenderRCrit = f.getTarget().getAttribute().getRCrit() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double attackerCrit = attacker.getAttribute().getCrit() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double result = attackerCrit - defenderRCrit;

		if (result < D.LOW_CRIT_PROBABILITY) {
			result = D.LOW_CRIT_PROBABILITY;
		}

		if (result > D.HIGH_CRIT_PROBABILITY) {
			result = D.HIGH_CRIT_PROBABILITY;
		}

		return Random.isHappen((float) result);
	}

	private boolean isDodge(FighterBeAttack f) {

		SkillTemplet templet = getTemplet();
		if (templet.getCommonAttack() == 0) { // 技能不会闪避
			return false;
		}

		double defenderDodge = f.getTarget().getAttribute().getDodge() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double attackerHit = attacker.getAttribute().getHit() / D.ER_JI_SHU_XING_SHANG_XIAN_ZHI;
		double result = defenderDodge - attackerHit;

		if (result < D.LOW_DODGE_PROBABILITY) {
			result = D.LOW_DODGE_PROBABILITY;
		}

		if (result > D.HIGH_DODGE_PROBABILITY) {
			result = D.HIGH_DODGE_PROBABILITY;
		}

		return Random.isHappen((float) result);
	}

	protected BattleCamp getTargetCamp(AttackType at) {
		return at.isReleaseToFriend() ? getFriends() : getEnemy();
	}

	protected void update(final AttackType at, final FighterBeAttack f,
			final Fighter target, final int damage) {

		switch (at.getAttakMode()) {

		case ADD:

			target.addHp((int) (damage * f.getDecay()));

			break;

		case REDUCE:

			if (target.isDeath()) {
				return;
			}

			target.reduceHp((int) (damage * f.getDecay()));
			break;

		case NONE:
			break;
		}
	}

	// @SuppressWarnings("deprecation")
	protected void createBuff(final Fighter target) {

		int type = attacker.getTypeId();

		IFighterTemplet temp = FighterConfig.get(type);

		if (temp == null) { // 其他战士不处理, 只处理神将
			return;
		}

		int skill = temp.getSkill();

		if (skill != this.id) { // 天赋技能才 触发
			return;
		}

		int al = attacker.getLevel();
		int tl = target.getLevel();
		float pro = temp.getEffectOdds();

		if (isDogz(temp)) {
			pro = (float) Script.Dogz.getTriggerOdds(al, tl, pro);
		}

		final int id = temp.getSuffId();

		if (BuffTempletConfig.get(id) == null) {
			return;
		}

//		int star = attacker.getStar();
		if (D.IS_BUFF_OPEN && (Random.isHappen(pro) /*&& star >= 2*/)) {
			Buffer newBuff = newBuff(id, target);
			Debuger.debug("logic: AbstractSkill.createBuff() 生成Buff: "
					+ newBuff + " round = " + battle.getRound());
		}
	}

	private boolean isDogz(IFighterTemplet temp) {
		return DogzTempletConfig.get(temp.getId()) != null;
	}

	private Buffer newBuff(final int id, final Fighter target) {

		final BufferManager bm = target.getBufferManager();

		boolean isUnder = battle.getUnder().contains(target);
		int position = isUnder ? battle.getUnder().getPosition(target) : battle
				.getUpper().getPosition(target);

		final Buffer buffer = new BufferImpl(id, target, this, isUnder,
				position);

		bm.add(buffer);

		return buffer;
	}

	@Override
	public int getStep() {
		return getTemplet().getStage();
	}

	@Override
	public boolean isEquipment() {
		return true;
	}
}
