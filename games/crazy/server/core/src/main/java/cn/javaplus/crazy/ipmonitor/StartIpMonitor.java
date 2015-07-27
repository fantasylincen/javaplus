package cn.javaplus.crazy.ipmonitor;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.server.ServerStartEvent;

public class StartIpMonitor implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		ServerIpMonitor s = new ServerIpMonitor();
		s.start();
	}
}
