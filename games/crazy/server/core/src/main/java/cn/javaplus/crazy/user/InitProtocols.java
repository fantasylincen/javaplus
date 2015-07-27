package cn.javaplus.crazy.user;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.handler.CreateProtocolsEvent;

public class InitProtocols implements Listener<CreateProtocolsEvent> {

	@Override
	public void onEvent(CreateProtocolsEvent e) {
		Game.setProtocols(e.getProtocols());
	}

}
