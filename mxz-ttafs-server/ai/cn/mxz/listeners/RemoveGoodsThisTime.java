package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.AfterRequestEvent;
import cn.mxz.events.Listener;
import cn.mxz.temp.TempCollection;
import cn.mxz.temp.TempKey;

public class RemoveGoodsThisTime implements Listener<AfterRequestEvent> {

	@Override
	public void onEvent(AfterRequestEvent event) {
		City user = event.getUser();
		if (user != null) {
			TempCollection c = user.getTempCollection();
			c.remove(TempKey.BUY_PROP_THIS_TIME);
			c.remove(TempKey.WILL_BUY_PROP_THIS_TIME);
		}
	}

}
