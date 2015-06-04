package cn.mxz.listeners;

import message.S;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerPropertyChangeEvent;

//每次扣除金贝壳时, 弹出提示
public class ShowMessageWhileReduceJinBeiKe implements
		Listener<PlayerPropertyChangeEvent> {

	@Override
	public void onEvent(PlayerPropertyChangeEvent e) {

		if (e.getPlayerProperty() == PlayerProperty.NEW_GOLD && !e.isAdd()) {

			City city = e.getCity();
			city.getMessageSender().send(S.S60122);
		}
	}

}
