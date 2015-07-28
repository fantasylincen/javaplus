package cn.javaplus.crazy.server;

import cn.mxz.base.server.Server;

public class ServerStartEvent {

	private Server server;

	public ServerStartEvent(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}

}
