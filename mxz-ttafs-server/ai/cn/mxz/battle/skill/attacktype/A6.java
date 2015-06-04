package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

@Component("attackType6")

public class A6 implements AttackType {

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<Fighter> all = ans.getLives();
		return AttackTypeUtil.buildList(all);
	}

	@Override
	public AttackMode getAttakMode() {
		return AttackMode.REDUCE;
	}

	@Override
	public boolean isReleaseToFriend() {
		return false;
	}

}
