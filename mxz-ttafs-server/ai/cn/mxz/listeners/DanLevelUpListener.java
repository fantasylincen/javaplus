package cn.mxz.listeners;

import cn.mxz.events.Listener2;
import cn.mxz.pvp.DanLevelUpEvent;

class DanLevelUpListener implements Listener2 {

	@Override
	public void onEvent(Object e) {
//		PvpPlayer player = (PvpPlayer) e.getSource();
//
//		City city = CityFactory.getCity(player.getUname());
//
//		ISocket socket = city.getSocket();
//
//		MessageFactory.getPvp().onLevelUp(socket, player.getDanId());
	}

	@Override
	public Class<?> getEventListendClass() {
		return DanLevelUpEvent.class;
	}

}
