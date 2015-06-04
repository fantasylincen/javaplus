package core;

import java.io.IOException;

import org.xsocket.connection.INonBlockingConnection;

public interface IGameLogic {

	/**
	 * 包处理器
	 * @param con
	 * @param packageNo		包号
	 * @param data			除包头，包尾，包号，包长的附加信息的数据
	 * @throws IOException 
	 */
	void packageRun( INonBlockingConnection con, short packageNo, byte[] data ) throws IOException;
	
	/**
	 * 登陆服 包处理器
	 * @param con
	 * @param packageNo		包号
	 * @param data			除包头，包尾，包号，包长的附加信息的数据
	 * @throws IOException 
	 */
	void loginPakRun( INonBlockingConnection con, short packageNo, byte[] data ) throws IOException;
	
	/**
	 * 玩家关闭连接，退出游戏
	 * @param con
	 */
	void userExit( INonBlockingConnection con ) throws IOException;

}
