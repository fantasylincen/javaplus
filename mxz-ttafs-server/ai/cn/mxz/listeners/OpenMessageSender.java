package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.AfterRequestEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.MessageSenderSwitch;

//某个请求 从新开启MessageSender
public class OpenMessageSender implements Listener<AfterRequestEvent> {


	@Override
	public void onEvent(AfterRequestEvent e) {
		City user = e.getUser();
		if(user != null) {

			int id = e.getPacketId();

			if(MessageSenderSwitch.contains(id)) {
				MessageSenderSwitch.open(user);
			}
		}
	}
}
