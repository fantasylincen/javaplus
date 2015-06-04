package cn.mxz.activity.boss;

import cn.mxz.protocols.user.boss.BossP.BossPro.ChallengerPro;
import cn.mxz.user.builder.UserBaseBuilder;

class ChallengerBuilder {

	public ChallengerPro build(BossChallenger bc, Boss boss) {

		ChallengerPro.Builder b = ChallengerPro.newBuilder();

		b.setDamage(bc.getDamage(boss));

		b.setIsKiller(bc.isKiller(boss));

		b.setRank(bc.getRank(boss));

		b.setUser(new UserBaseBuilder().build(bc.getCity().getPlayer()));

		b.setType(1);

		return b.build();
	}

}
