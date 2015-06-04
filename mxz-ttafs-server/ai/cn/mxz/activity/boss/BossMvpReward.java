package cn.mxz.activity.boss;

import cn.mxz.PrizeInExcel;
import cn.mxz.city.City;

public class BossMvpReward implements PrizeInExcel {

	private Boss	boss;

	private City	city;

	BossMvpReward(Boss boss, City city) {

		this.boss = boss;

		this.city = city;
	}

	/**
	 * MVP奖励和击杀奖励
	 */
	@Override
	public String getAwards() {

		// BossChallenger mvp = boss.getMvp();
		//
		// String rewards = "";
		//
		// if (mvp != null && mvp.getId().equals(city.getId())) {
		//
		// BossEventTemplet t = BossRewardConfig.get(3, boss);
		//
		// rewards += t.getReward() + "|";
		// }
		//
		// return rewards;
		return "";

	}

}
