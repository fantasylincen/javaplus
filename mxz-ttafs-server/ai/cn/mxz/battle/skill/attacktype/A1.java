package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

@Component("attackType1")
@Scope("prototype")

public class A1 implements AttackType {

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		Fighter target = AttackTypeUtil.getTarget(ans, position);

		return AttackTypeUtil.buildList(

				AttackTypeUtil.build(target, 1)
				);
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
