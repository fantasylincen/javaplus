package cn.mxz.mission.type;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.battle.MissionBattle;
import cn.mxz.mission.old.PropPrize;
import cn.mxz.protocols.user.mission.MissionP.MissionPrizePro;

public class MissionPrizeBuilder {

	public MissionPrizePro build(MissionBattle battle) {

		List<PropPrize> ps = battle.getPropPrize();

		List<BattleExpPrize> f = battle.getExpPrize();

		final MissionPrizePro.Builder b = MissionPrizePro.newBuilder();

		for (final BattleExpPrize fp : f) {

			b.addPrizes(new FighterPrizeBuilder().build(fp));
		}

		for (PropPrize pr : ps) {

			b.addProps(new PropPrizeBuilder().build(pr));
		}

//		b.addProps(new PropBuilder().build(135104, 1));

		b.setStar(battle.getStar());

		return b.build();
	}


}
