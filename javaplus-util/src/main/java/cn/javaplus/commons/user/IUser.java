package cn.javaplus.commons.user;

import cn.javaplus.commons.socket.ISocket;

/**
 * 用户
 * @author 林岑
 *
 */
public interface IUser {

	/***
	 * 用户ID
	 */
	String getId();

	/**
	 * 用户套接字
	 */
	ISocket getSocket();

	/**
	 *用户等级
	 * @return
	 */
	int getLevel();
}
