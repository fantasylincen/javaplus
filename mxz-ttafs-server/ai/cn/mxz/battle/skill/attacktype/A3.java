package cn.mxz.battle.skill.attacktype;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

@Component("attackType3")
@Scope("prototype")

public class A3 implements AttackType {


	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		Fighter target = AttackTypeUtil.getTarget(ans, position);

		int t = ans.getPosition(target);

//		  1       1
//		3 4 5   5 4 3
//		  7       7

		if(t == 7 || t == 4 || t == 1) {
			List<Fighter> fs = AttackTypeUtil.getFighters(ans, 7, 4, 1);
			Iterator<Fighter> it = fs.iterator();
			while (it.hasNext()) {
				Fighter fighter = (Fighter) it.next();
				if(fighter.isDeath()) {
					it.remove();
				}
			}

			return AttackTypeUtil.buildList(fs);
		}


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
