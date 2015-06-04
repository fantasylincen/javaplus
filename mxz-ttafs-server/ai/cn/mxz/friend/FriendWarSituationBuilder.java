package cn.mxz.friend;

import cn.mxz.battle.WarSituation;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.protocols.user.friend.FriendAppayMessageListP.FriendWarSituationPro;

public class FriendWarSituationBuilder {

	public FriendWarSituationPro build(FriendBattle battle) {
		WarSituation ws = battle.getWarSituation();
		FriendWarSituationPro.Builder b = FriendWarSituationPro.newBuilder();
		b.setSituation(new WarSituationBuilder().build(battle.getUnderPlayerCamp().getCity(), ws));
		return b.build();
	}

}
