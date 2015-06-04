package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;
import cn.mxz.gm.GMServer;

//启动GM服务器
public class StartGMServer implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		new GMServer().start();
	}


}
