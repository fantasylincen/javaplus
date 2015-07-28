package cn.javaplus.twolegs.base;

public class GameServerStartEvent {

	private GameServer server;

	public GameServerStartEvent(GameServer server) {
		this.server = server;
	}
	
	public GameServer getGameServer() {
		return server;
	}

}
