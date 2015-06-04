package cn.mxz.init;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.server.AIServer;
import cn.mxz.base.server.Server;

/**
 * 服务器工厂
 * @author 	林岑
 * @since	2013年6月6日 15:15:44
 *
 */

class ServerFactory {

	ServerFactory() {
	}

	/**
	 * 构造一个服务器
	 * @param gamePort	服务器端口
	 * @param args 启动参数
	 * @return
	 */
	public static Server createServer(int gamePort, String[] args) {
		return new AIServer(ServerConfig.getGamePort());
	}
}
