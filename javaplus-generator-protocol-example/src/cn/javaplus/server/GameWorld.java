package cn.javaplus.server;

import java.net.Socket;

import cn.javaplus.comunication.ProtocolUser;

/**
 * 游戏世界
 */
public class GameWorld {

	/**
	 * 获得指定账号的玩家
	 * @param userId
	 * @param socket
	 * @return
	 */
	public static ProtocolUser getUser(String userId, Socket socket) {
		return new TestUser("test", socket);
	}

}
