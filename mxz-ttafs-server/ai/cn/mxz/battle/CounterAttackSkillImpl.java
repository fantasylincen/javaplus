package cn.mxz.battle;

import java.util.List;

import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.battle.skill.attacktype.AttackTypeUtil;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

import db.domain.Skills;

class CounterAttackSkillImpl extends AbstractSkill {

	private Fighter	defender;

	/**
	 *
	 * @param battle
	 * @param attacker 反击的战士
	 * @param defender
	 */
	CounterAttackSkillImpl(Battle battle, Fighter attacker, Fighter defender) {
		super(attacker.getNormalSkillId());
		this.battle = battle;
		this.attacker = attacker;
		this.defender = defender;
	}

	@Override
	public List<FighterBeAttack> fire() {

		final BattleCamera bc = new BattleCameraImpl();

		bc.snapshot(attacker, battle, getId()); // 战场快照

		List<FighterBeAttack> bs = Lists.newArrayList(AttackTypeUtil.build(defender, 1));

		attack(bs);

		List<Buffer> ls = Lists.newArrayList();
		
		AttackAction action = bc.contrast(bs, battle, ls);

		battle.getWarSituation().add(action);// 与之前快照进行对比

		action.setIsCounterAttack(true);

		return bs;
	}

	private void attack(List<FighterBeAttack> bs) {

		int damage = getDamage(defender);

		FighterBeAttack f = bs.get(0);

		xxx(damage, f);

		defender.reduceHp((int) (damage * f.getDecay()));
	}

	@Override
	public int getFighterId() {
		return attacker.getTypeId();
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
	public int getShenJia() {

		SkillTemplet templet = SkillTempletConfig.get(getId());
		return (int) (templet.getSocial() + (getLevel() - 1) * templet.getSocialGrow());
	}

	@Override
	public Skills getDto() {
		return null;
	}

	@Override
	public int getLevel() {
		return 1;
	}
}
