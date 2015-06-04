package cn.mxz.battle.skill.attacktype;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

@Component("attackType22")
@Scope("prototype")

public class A22 implements AttackType {

	@Override
	public List<FighterBeAttack> getFighters(BattleCamp ans, int position) {

		List<Fighter> lives = ans.getLives();

		if (lives.isEmpty()) {
			return Lists.newArrayList();
		}

		Collections.sort(lives, new Comparator<Fighter>() {

			@Override
			public int compare(Fighter o1, Fighter o2) {

				return o2.getHpNow() - o1.getHpNow();
			}

		});

		return AttackTypeUtil.buildList(AttackTypeUtil.build(lives.get(0), 1));
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
