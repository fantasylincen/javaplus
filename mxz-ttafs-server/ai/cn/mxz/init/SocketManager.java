package cn.mxz.init;


import cn.mxz.city.City;
import cn.mxz.user.init.ReadyUser;

import com.lemon.commons.socket.ISocket;

/**
 *
 * 用户初始化工具
 *
 * 用于管理用户和用户的套接字
 *
 * @author 	林岑
 * @since	2013年5月27日 11:18:37
 *
 */
public interface SocketManager {

	/**
	 * 根据套接字取得这个套接字对应的用户
	 * @param s	套接字
	 * @return
	 */
	City getUser(ISocket socket);

	/**
	 * 准备用户信息
	 */
	void ready(ISocket s, ReadyUser u);

	/**
	 * 取消s和某user的绑定状态
	 * @param s
	 */
	void unbind(ISocket old);

	/**
	 * 根据用户, 拿到对应的套接字
	 * @param u
	 * @return
	 */
	ISocket getSocket(City city);

	/**
	 * 绑定用户和他的套接字
	 */
	void bind(ISocket socket, City city);

	/**
	 * 得到用户准备信息
	 * @return	该用户对应的套接字
	 */
	ReadyUser getReady(ISocket socket);

	/**
	 * 当任何socket断开连接的时候, 都会执行该方法
	 * @param socket
	 */
	void onDestroy(ISocket socket);

	/**
	 * 移除待创建的用户信息
	 * @param s
	 */
	void removeReadyUser(ISocket s);
}
