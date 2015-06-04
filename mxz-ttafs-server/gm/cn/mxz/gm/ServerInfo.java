package cn.mxz.gm;

import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.prizecenter.PropIdCheck;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class ServerInfo  extends AbstractHandler {

	String ret;
	
	ServerInfo(){
		//{
//	    "options": [
//	                "Option 1",
//	                "Option 2",
//	                "Option 3",
//	                "Option 4",
//	                "Option 5"
//	            ]
//	        }
		ret = "var serverName=\"" + ServerConfig.getConfig().getName() + "\";";
		ret += "var gmPort=\"" + ServerConfig.getConfig().getGameManagerPort() + "\";";
		ret += "var gameId=\"" + ServerConfig.getConfig().getId() + "\";";
		ret += "var port=\"" + ServerConfig.getConfig().getPort() + "\";";
		ret += "var dbPath=\"" + ServerConfig.getConfig().getDataBasePath() + "\";";
		ret += "var erating=\"" + ServerConfig.getConfig().getEratingPath() + "\";";
		ret += "var rechargePort=\"" + ServerConfig.getConfig().getRechargePort() + "\";";
		
	}
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
//		System.out.println( "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		return ret;
	}


}
