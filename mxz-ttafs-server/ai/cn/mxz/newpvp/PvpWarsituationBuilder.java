package cn.mxz.newpvp;

import cn.mxz.protocols.pvp.PvpP.PvpWarsituationPro;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.util.debuger.Debuger;

class PvpWarsituationBuilder {

	public PvpWarsituationPro build(RandomRewardSender s, boolean isLastUpMatch, boolean isUp, WarSituationPro build, int winPoint) {
		PvpWarsituationPro.Builder b = PvpWarsituationPro.newBuilder();
		b.setWarSituation(build);

		Debuger.debug("PvpWarsituationBuilder.build() "  + isLastUpMatch + "," + isUp );

		if (!isLastUpMatch) {
			b.setUpStatus(-1);
		} else {
			b.setUpStatus(isUp ? 0 : 1);
		}
		
		b.setWinPoint(winPoint);

		
		
		b.setReward(new CardRewardBuilder().build(s));

		return b.build();
	}

}
