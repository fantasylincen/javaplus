package cn.mxz.activity.boss;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.mission.old.MissionPrize;
import cn.mxz.mission.old.PropPrize;

import com.google.common.collect.Lists;


public class BossMissionPrize implements MissionPrize {

	private List<BattleExpPrize>	prize;

	public BossMissionPrize(List<BattleExpPrize> prize) {
		this.prize = prize;
	}

	@Override
	public List<BattleExpPrize> getFighterPrize() {
		return prize;
	}

	@Override
	public List<PropPrize> getPropPrizes() {
		return Lists.newArrayList();
	}

	@Override
	public int getStar() {
		return 0;
	}

}
