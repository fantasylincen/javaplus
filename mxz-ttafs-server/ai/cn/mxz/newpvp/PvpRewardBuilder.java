package cn.mxz.newpvp;

import cn.mxz.protocols.pvp.PvpP.PvpRewardPro;

class PvpRewardBuilder {

	public PvpRewardPro build(PvpReward reward) {
		PvpRewardPro.Builder b = PvpRewardPro.newBuilder();
		b.setHasReceived(reward.hasReceived());
		return b.build();
	}

}
