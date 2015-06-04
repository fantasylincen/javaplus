package cn.mxz.dogz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.battle.skill.attacktype.AttackType;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.fighter.Fighter;
import cn.mxz.util.Factory;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.domain.Skills;

public class DogzSkillInBattle extends AbstractSkill implements SkillInBattle {

	private Dogz	dogz;

	public DogzSkillInBattle(Dogz dogz) {
		super(getId(dogz));
		this.dogz = dogz;
	}

	private static int getId(Dogz dogz) {
		DogzTemplet temp = DogzTempletConfig.get(dogz.getTypeId());
		int dogzSikll = temp.getDogzSikll();
		return dogzSikll;
	}

	@Override
	public int getFighterId() {
		return dogz.getTypeId();
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public int getIdentification() {
		return dogz.getTypeId();
	}

	@Override
	public Skills getDto() {
		return null;
	}

	@Override
	public int getLevel() {
		return dogz.getLevel();
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
	public Fighter getFighter() {
		return dogz;
	}

	@Override
	public void setAttacker(Fighter fighter) {
		this.attacker = fighter;
	}

	@Override
	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	@Override
	public List<FighterBeAttack> fire() {

		int attackType = getTemplet().getAttackType();

		Debuger.debug("DogzSkillInBattle.fire()" + attackType);

		AttackType at = (AttackType) Factory.get("attackType" + attackType);

		if (getTargetCamp(at).isAllDeath()) {
			return Lists.newArrayList();
		}

		return attack(at);
	}

	@Override
	protected List<FighterBeAttack> attack(AttackType at) {
		final BattleCamp camp = getTargetCamp(at);

		if (camp.isAllDeath()) {

			return new ArrayList<FighterBeAttack>();
		}

		List<FighterBeAttack> all = at.getFighters(camp, 4);

		all = new ArrayList<FighterBeAttack>(all);

		Iterator<FighterBeAttack> it = all.iterator();

		while (it.hasNext()) {

			FighterBeAttack f = it.next();

			final Fighter target = f.getTarget();

			if (target.isDeath()) { // 如果死了, 就不要打他

				it.remove();

				continue;
			}

			createBuff(target);

			int damage = getDamage(target);

			damage = xxx(damage, f);

			update(at, f, target, damage);
		}

		if (all.isEmpty()) {

			throw new RuntimeException("被攻击战士列表为空!" + at);
		}

		return all;
	}

}
