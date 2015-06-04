package cn.mxz.listeners.init;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.util.debuger.Debuger;

public class LogCreateUser implements Listener<UserCreateEvent> {

	@Override
	public void onEvent(UserCreateEvent event) {
		City city = event.getCity();
		Debuger.debug("createUser:" + city.getId());
	}

}
