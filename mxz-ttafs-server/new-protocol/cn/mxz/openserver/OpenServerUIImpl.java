package cn.mxz.openserver;

import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class OpenServerUIImpl implements OpenServerUI {

	private List<OpenServerReward> rewards;
	private City user;

	public OpenServerUIImpl(List<OpenServerReward> rewards, City user) {
		this.rewards = rewards;
		this.user = user;
	}

	@Override
	public List<OpenServerRewardUI> getRewards() {
		List<OpenServerRewardUI> ls = Lists.newArrayList();

		for (OpenServerReward o : rewards) {
			ls.add(new OpenServerRewardUIImpl(o, user));
		}
		return ls;
	}

}
