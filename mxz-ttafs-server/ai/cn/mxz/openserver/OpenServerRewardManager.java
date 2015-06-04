package cn.mxz.openserver;

import java.util.List;

import cn.mxz.OpenServicerTemplet;
import cn.mxz.OpenServicerTempletConfig;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

public class OpenServerRewardManager {

	private City city;

	public OpenServerRewardManager(City city) {
		this.city = city;
	}

	public List<OpenServerReward> getRewards() {
		List<OpenServicerTemplet> all = OpenServicerTempletConfig.getAll();
		List<OpenServerReward> ls = Lists.newArrayList();
		for (OpenServicerTemplet t : all) {
			ls.add(new OpenServerReward(t, city));
		}
		return ls;
	}

	public boolean isOpenToday() {

		UserCounter c = city.getUserCounter();
		return c.isMark(CounterKey.IS_OPEN_SEV_REWARD_OPEN);
	}

	public void openTodayReward() {
		if (isOpenToday()) {
			return;
		}

		UserCounter his = city.getUserCounterHistory();
		his.add(CounterKey.OPEN_SERVER_REWARD_PROGRESS, 1);

		UserCounter c = city.getUserCounter();
		c.mark(CounterKey.IS_OPEN_SEV_REWARD_OPEN);
	}

}
