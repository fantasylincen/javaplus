package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

@Component("attackType24")
@Scope("prototype")

public class A24 implements AttackType{

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<Fighter> all = ans.getLives();
		return AttackTypeUtil.buildList(AttackTypeUtil.build(cn.javaplus.util.Util.Collection.getRandomOne(all), 1));
	}

	@Override
	public AttackMode getAttakMode() {
		return AttackMode.ADD;
	}

	@Override
	public boolean isReleaseToFriend() {
		return true;
	}
}
