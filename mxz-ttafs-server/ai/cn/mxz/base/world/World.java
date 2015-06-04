package cn.mxz.base.world;

import java.util.Map;

import cn.javaplus.user.IUserCollection;
import cn.mxz.base.server.Server;
import cn.mxz.city.City;
import cn.mxz.init.SocketManager;
import cn.mxz.system.SystemCounter;

/**
 *
 * 世界
 * @author 林岑
 * @since	2013年6月1日 23:29:25
 *
 */
public interface World extends IUserCollection<City>{

	/**
	 * 用户套接字管理器
	 * @return
	 */
	SocketManager getSocketManager();

	/**
	 * 判断uId这个用户是否在线
	 * @param uId
	 * @return
	 */
	boolean isOnline(String uId);

	/**
	 * 最近30天登陆过的用户城池
	 * @return
	 */
	Map<String, City> getNearests();

	/**
	 * 重新加载世界
	 */
	void reload();

	/**
	 * 世界是否被锁定
	 * @return
	 */
	boolean isLock();

	/**
	 * 昵称管理器
	 * @return
	 */
	NickManager getNickManager();

	/**
	 * 系统计数器
	 * @return
	 */
	SystemCounter getCounter();

	/**
	 * 服务器
	 * @return
	 */
	Server getServer();
}
