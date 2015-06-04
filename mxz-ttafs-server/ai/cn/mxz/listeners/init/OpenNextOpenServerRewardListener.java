package cn.mxz.listeners.init;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.init.EnterGameEvent;
import cn.mxz.openserver.OpenServerRewardManager;

/**
 * 开启一个新的开服礼包
 * @author 林岑
 *
 */
public class OpenNextOpenServerRewardListener implements Listener<EnterGameEvent> {

	@Override
	public void onEvent(EnterGameEvent e) {
		City city = e.getCity();
		OpenServerRewardManager om = city.getOpenServerRewardManager();
		if(!om.isOpenToday()) {
			om.openTodayReward();
		}
	}

}
