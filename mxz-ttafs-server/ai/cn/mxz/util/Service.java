package cn.mxz.util;

import cn.mxz.city.City;
import cn.mxz.user.Player;

import com.lemon.commons.socket.ISocket;

/**
 * 服务
 * @author 林岑
 */
public interface Service {

	ISocket getSocket();

	/**
	 * 用户
	 * @return
	 */
	City getCity();

	/**
	 * 用户
	 * @return
	 */
	City getCityNullAble();

	/**
	 * 玩家
	 * @return
	 */
	Player getPlayer();

	void init(ISocket socket);


}
