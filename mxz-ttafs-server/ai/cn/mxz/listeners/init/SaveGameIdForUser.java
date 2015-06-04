package cn.mxz.listeners.init;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.init.EnterGameEvent;

/**
 * 开启一个新的开服礼包
 * 
 * @author 林岑
 * 
 */
public class SaveGameIdForUser implements Listener<EnterGameEvent> {

	@Override
	public void onEvent(EnterGameEvent e) {
		try {
			City city = e.getCity();
			String gameId = e.getGameId();
			city.getGameIdManager().save(gameId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
