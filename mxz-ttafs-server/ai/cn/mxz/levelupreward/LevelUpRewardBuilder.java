package cn.mxz.levelupreward;

import cn.mxz.protocols.user.LevelUpRewardP.LevelUpRewardPro.LevelUpRewardItemPro;
import cn.mxz.protocols.user.LevelUpRewardP.LevelUpRewardPro.LevelUpRewardItemPro.Builder;

public class LevelUpRewardBuilder {

	public LevelUpRewardItemPro build(Reward reward) {
		Builder b = LevelUpRewardItemPro.newBuilder();
		b.setHasReceived(reward.hasReceive());
		b.setLevel(reward.getNeedLevel());
		return b.build();
	}

}
