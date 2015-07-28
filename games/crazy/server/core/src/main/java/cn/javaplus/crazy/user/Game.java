package cn.javaplus.crazy.user;

import cn.javaplus.crazy.main.GameRoom;
import cn.javaplus.crazy.protocol.Protocols;
import cn.javaplus.crazy.waiting.WaitingRoom;

public class Game {
	private static Protocols protocols;
	private static WaitingRoom waitingRoom;
	private static GameRoom gameRoom;
	private static World world;

	public static World getWorld() {
		if (world == null)
			world = new World();
		return world;
	}

	public static Protocols getProtocols() {
		return protocols;
	}

	static void setProtocols(Protocols protocols) {
		Game.protocols = protocols;
	}

	public static WaitingRoom getWatingRoom() {
		if (waitingRoom == null) {
			waitingRoom = new WaitingRoom();
		}
		return waitingRoom;
	}

	public static GameRoom getGameRoom() {
		if (gameRoom == null) {
			gameRoom = new GameRoom();
		}
		return gameRoom;
	}
}
