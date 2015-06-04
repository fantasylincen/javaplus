package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.BeforeRequestEvent;
import cn.mxz.events.Listener;
import cn.mxz.system.MessageSenderSwitch;

//某个请求关闭 MessageSender
public class CloseMessageSender implements Listener<BeforeRequestEvent> {

	@Override
	public void onEvent(BeforeRequestEvent e) {
		City user = e.getUser();
		if (user != null) {

			int id = e.getPacketId();

			if (MessageSenderSwitch.contains(id)) {
				MessageSenderSwitch.close(user);
			} else {
				MessageSenderSwitch.open(user);// 打开, 确保意外关闭
			}
		}
	}

}
