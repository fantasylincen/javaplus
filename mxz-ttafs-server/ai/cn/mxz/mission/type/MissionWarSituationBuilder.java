package cn.mxz.mission.type;

import cn.mxz.battle.MissionBattle;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.protocols.user.mission.MissionP.MissionWarSituationPro;

public class MissionWarSituationBuilder {

	public MissionWarSituationPro build(MissionBattle battle) {

		MissionWarSituationPro.Builder b = MissionWarSituationPro.newBuilder();

		b.setWarSituation(new WarSituationBuilder().build(battle.getUnderPlayerCamp().getCity(), battle.getWarSituation()));

		b.setPrize(new MissionPrizeBuilder().build(battle));

		return b.build();
	}

}
