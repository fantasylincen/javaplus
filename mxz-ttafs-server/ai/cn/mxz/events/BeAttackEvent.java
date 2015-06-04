package cn.mxz.events;

import java.util.List;

import cn.mxz.battle.Battle;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

public class BeAttackEvent {

	private List<FighterBeAttack> beAttacks;

	private Fighter attacker;

	private Battle battle;

	private SkillInBattle skill;


	public BeAttackEvent(List<FighterBeAttack> beAttacks, Fighter attacker,
			Battle battle, SkillInBattle skill) {
		super();
		this.beAttacks = beAttacks;
		this.attacker = attacker;
		this.battle = battle;
		this.skill = skill;
	}

	public List<FighterBeAttack> getBeAttacks() {
		return beAttacks;
	}

	public Fighter getAttacker() {
		return attacker;
	}

	public Battle getBattle() {
		return battle;
	}

	public void setBeAttacks(List<FighterBeAttack> beAttacks) {
		this.beAttacks = beAttacks;
	}

	public void setAttacker(Fighter attacker) {
		this.attacker = attacker;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public SkillInBattle getSkill() {
		return skill;
	}

	/**
	 * 是不是上方战士被攻击了
	 * @return
	 */
	public boolean isUpperBeAttack() {
		if(beAttacks.isEmpty() ) {
			return false;
		}
		Fighter f = beAttacks.get(0).getTarget();
		return battle.getUpper().contains(f);
	}

}
