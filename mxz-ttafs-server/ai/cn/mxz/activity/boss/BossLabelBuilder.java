package cn.mxz.activity.boss;

import cn.mxz.city.City;
import cn.mxz.protocols.user.boss.BossP.BossListPro.BossLabelPro;
import cn.mxz.user.builder.UserBaseBuilder;

class BossLabelBuilder {

	public BossLabelPro build(Boss boss, City city) {

		BossLabelPro.Builder b = BossLabelPro.newBuilder();

		b.setId(boss.getId());

		b.setTempletId(boss.getTempletId());

		b.setLevel(boss.getLevel());

		b.setFinder(new UserBaseBuilder().build(boss.getFinder().getPlayer()));

		b.setRemainSec(boss.getRemainSec());

		int status = boss.getStatus(city);

		// FighterTemplet t = FighterTempletConfig.get(boss.getId());

//		Debuger.debug("BossLabelBuilder.build()" + status + ", " + boss.getRemainSec() + ", " + boss.getLevel()/*
//																												 * +
//																												 * ", "
//																												 * +
//																												 * t
//																												 * !=
//																												 * null
//																												 * ?
//																												 * t
//																												 * .
//																												 * getName
//																												 * (
//																												 * )
//																												 * :
//																												 * "-"
//																												 */);

		b.setStatus(status);

		b.setIsSuperBoss(boss.isSuperBoss());

		return b.build();
	}
}
