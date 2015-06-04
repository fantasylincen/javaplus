package cn.mxz.activity.boss;

import cn.mxz.PrizeInExcel;

class BossKillReward implements PrizeInExcel {

	private Boss	boss;

	BossKillReward(Boss boss) {

		this.boss = boss;
	}

	@Override
	public String getAwards() {

//		BossEventTemplet t = BossRewardConfig.get(4, boss);
//
//		return t.getReward() + "|";
		return "";

	}

}
