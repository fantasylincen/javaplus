package cn.mxz.battle.skill.attacktype;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

/**
 *
 * 全体加血
 * @author 林岑
 *
 */
@Component("attackType23")
@Scope("prototype")

public class A23 implements AttackType{

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<Fighter> all = ans.getLives();
		return AttackTypeUtil.buildList(all);
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
