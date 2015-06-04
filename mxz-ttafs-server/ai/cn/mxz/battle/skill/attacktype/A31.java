package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;

@Component("attackType31")
@Scope("prototype")

public class A31 implements AttackType {

	@Override
	public List<FighterBeAttack> getFighters( BattleCamp ans, int position) {
		return AttackTypeUtil.buildList(AttackTypeUtil.build(ans.get(position), 1));
	}

	@Override
	public AttackMode getAttakMode() {
		return AttackMode.NONE;
	}

	@Override
	public boolean isReleaseToFriend() {
		return true;
	}
}