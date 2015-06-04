package cn.mxz.openserver;

import cn.mxz.city.City;

public class OpenServerRewardUIImpl implements OpenServerRewardUI {

	private OpenServerReward reward;

	public OpenServerRewardUIImpl(OpenServerReward reward, City user) {
		this.reward = reward;
	}

	public int getDay() {
		return reward.getDay();
	}

	public boolean getHasReceive() {
		return reward.getHasReceive();
	}

	public boolean getCanReceive() {
		return reward.getCanReceive();
	}

}
