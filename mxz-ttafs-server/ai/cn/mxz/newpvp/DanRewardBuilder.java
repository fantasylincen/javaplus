package cn.mxz.newpvp;

import cn.mxz.protocols.pvp.PvpP.DanRewardPro;

public class DanRewardBuilder {

	public DanRewardPro build(DanReward danReward) {
		DanRewardPro.Builder b = DanRewardPro.newBuilder();
		b.setCanReceive(danReward.canReiceive());
		String reward = danReward.getReward();
		b.setRewards(reward);
		int id = danReward.getId();
		b.setDanId(id);
		return b.build();
	}

}
