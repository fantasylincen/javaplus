package cn.mxz.fighter;

import java.util.List;

import cn.mxz.UnionSkillParTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.base.AbstractSkill;
import cn.mxz.skill.SkillDamageCalc;
import cn.mxz.skill.SkillDamageCalc.Addition;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ITianFuSkill;

import com.google.common.collect.Lists;

import db.domain.Skills;

public class BattleUnionSkill extends AbstractSkill {

	private int				fighterId;
	private List<Integer>	ids;

	public BattleUnionSkill(int id, List<Integer> ids, Battle battle, Fighter attacker) {
		super(id);
		this.fighterId = attacker.getTypeId();
		this.ids = ids;
		this.battle = battle;
		this.attacker = attacker;
	}

	@Override
	public int getFighterId() {
		return fighterId;
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
	public Skills getDto() {
		return null;
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public int getShenJia() {
		return 0;
	}

	public List<Fighter> getFighters() {
		BattleCamp friends = battle.getFriends(attacker);
		List<Fighter> ls = Lists.newArrayList();
		for (Fighter fighter : friends.getFighters()) {
			if (ids.contains(fighter.getTypeId())) {
				ls.add(fighter);
			}
		}
		return ls;
	}

	@Override
	public Addition getDamage() {

		List<ITianFuSkill> ts = getTianFuSkills();
		List<Addition> ls = Lists.newArrayList();

		for (ITianFuSkill t : ts) {
			ls.add(build(t));
		}

		int levelPingJun = getPingJunLevel(ts);

		return add(ls, levelPingJun);
	}

	private int getPingJunLevel(List<ITianFuSkill> ts) {
		int sum = 0;
		int count = 0;
		for (ITianFuSkill t : ts) {
			sum += t.getLevel();
			count ++;
		}
		if(count == 0) {
			return 1;
		}
		return sum / count;
	}

	private Addition build(ITianFuSkill t) {
		return new SkillDamageCalc().calc(t);
	}

	private List<ITianFuSkill> getTianFuSkills() {

		List<ITianFuSkill> ls = Lists.newArrayList();

		if(attacker instanceof Hero) {
			ls.add(buildTS((Hero) attacker));
		}


		List<Fighter> fs = getFighters();
		for (Fighter fighter : fs) {
			if(fighter instanceof Hero) {
				ls.add(buildTS((Hero) fighter));
			}
		}
		return ls;
	}

	private ITianFuSkill buildTS(Hero attacker) {
		return attacker.getTianFuSkill();
	}

	private Addition add(List<Addition> ls, int levelPingJun) {
		float aa = 0;
		for (Addition a : ls) {
			float v = a.getValue();
			aa += v;
		}
//		平均等级（四舍五入）	伤害系数加成
//		0	0
//		int	float
//		meanLv	harmPar
//		1	1
//		2	1.5
//		3	2
//		4	2.5
//		5	3
//		6	3.5

		aa *= UnionSkillParTempletConfig.get(levelPingJun).getHarmPar();

		return new Addition(aa, false);
	}

}
