package cn.mxz.system;

import cn.mxz.base.server.Server;

public class GameSystem {
	private static GameSystem instance;
	private Server server;

	private GameSystem() {
	}

	public static GameSystem getInstance() {
		if (instance == null) {
			instance = new GameSystem();
		}
		return instance;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public Server getServer() {
		return server;
	}
}
