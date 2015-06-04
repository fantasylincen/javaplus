package cn.mxz.base.server;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;

/**
 * 服务器状态
 *
 * @author 林岑
 *
 */
public class ServerStatus {

	/**
	 * 服务器ID
	 *
	 * @return
	 */
	public int getServerId() {
		return ServerConfig.getServerId();
	}

	/**
	 * 在线人数
	 *
	 * @return
	 */
	public int getOnlineSize() {
		return getWorld().getOnlineAll().size();
	}

	private World getWorld() {
		return WorldFactory.getWorld();
	}

	/**
	 * 总人数
	 *
	 * @return
	 */
	public int getSize() {
		return getWorld().getAll().size();
	}

}
