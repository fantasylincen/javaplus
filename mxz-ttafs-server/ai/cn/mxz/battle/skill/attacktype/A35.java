package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

@Component("attackType35")
@Scope("prototype")

public class A35 implements AttackType {

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<Fighter> ls = Lists.newArrayList();


		AttackTypeUtil.add(ls, ans, 7);

		if(!ls.isEmpty()) {
			return AttackTypeUtil.buildList(ls);
		}


		AttackTypeUtil.add(ls, ans, 3, 4, 5);

		if(!ls.isEmpty()) {
			return AttackTypeUtil.buildList(ls);
		}

		AttackTypeUtil.add(ls, ans, 1);

		return AttackTypeUtil.buildList(ls);
		//0 1 2
		//3 4 5
		//6 7 8
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
