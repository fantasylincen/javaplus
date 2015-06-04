package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.Listener2;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.mission.IMissionManager;

/**
 * 进入第一个副本
 * @author 林岑
 *
 */
public class EnterFirstMission implements Listener2 {

	@Override
	public void onEvent(Object e) {
		UserCreateEvent event = (UserCreateEvent) e;
		City city = event.getCity();
		IMissionManager mission = city.getMission();
		mission.enter(1);
	}

	@Override
	public Class<?> getEventListendClass() {
		return UserCreateEvent.class;
	}

}
