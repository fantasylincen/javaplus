package cn.mxz.tower;

import cn.mxz.protocols.user.tower.TowerP.FightingRewardPro;

/**
 * 最后一场战斗奖励
 * @author 林岑
 *
 */

public class FightingRewardBuilder {

	public FightingRewardPro build(TowerFightingReward fightingRewardLast) {

		FightingRewardPro.Builder b = FightingRewardPro.newBuilder();

		b.setCatchScore(fightingRewardLast.getCatchScore());

		b.setScore(fightingRewardLast.getScore());

		return b.build();
	}

}
