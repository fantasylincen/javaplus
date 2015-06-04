package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.SocketOnDestroyEvent;

//更新玩家战斗力缓存
public class UpdateShenJiaCache implements Listener<SocketOnDestroyEvent> {

	@Override
	public void onEvent(SocketOnDestroyEvent e) {
		City city = e.getUser();
		if (city != null) {
			city.getPlayer().updateShenJia();
		}
	}

}
