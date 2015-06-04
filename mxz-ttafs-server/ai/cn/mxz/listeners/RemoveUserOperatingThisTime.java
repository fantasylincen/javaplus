package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.AfterRequestEvent;
import cn.mxz.events.Listener;
import cn.mxz.temp.TempKey;

public class RemoveUserOperatingThisTime implements Listener<AfterRequestEvent> {

	@Override
	public void onEvent(AfterRequestEvent event) {
		City user = event.getUser();

		if (user != null) {
			user.getTempCollection().remove(TempKey.OPERATION_THIS_TIME);
		}
	}
}
