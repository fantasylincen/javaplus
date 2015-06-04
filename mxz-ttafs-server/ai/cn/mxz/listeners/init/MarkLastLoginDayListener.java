package cn.mxz.listeners.init;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.init.EnterGameEvent;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class MarkLastLoginDayListener implements Listener<EnterGameEvent> {

	@Override
	public void onEvent(EnterGameEvent e) {
		City city = e.getCity();
		int day = Util.Time.getCurrentDay();
		UserCounter his = city.getUserCounterHistory();
		his.set(CounterKey.LAST_LOGIN_DAY, day);
	}

}
