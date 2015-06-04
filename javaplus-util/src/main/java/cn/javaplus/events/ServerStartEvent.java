package cn.javaplus.events;

import cn.javaplus.base.server.Server;


/**
 * 服务器启动成功事件
 * @author 林岑
 *
 */
public class ServerStartEvent {

	private Server	server;

	public ServerStartEvent(Server server) {
		this.server = server;
	}

	public Server getServer() {
		return server;
	}
}
