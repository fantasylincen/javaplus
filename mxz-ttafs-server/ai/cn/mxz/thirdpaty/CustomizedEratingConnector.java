package cn.mxz.thirdpaty;

import cn.mxz.base.config.ServerConfig;

/**
 * mxz专门定制的Erating连接器
 * @author 林岑
 *
 */
class CustomizedEratingConnector extends EratingConnectorImpl1 {

	public CustomizedEratingConnector() {
		super(extracted());
	}

	private static String extracted() {
		String eratingPath = ServerConfig.getConfig().getEratingPath();
		return eratingPath;
	}

}
