package cn.javaplus.crazy.login;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.ServerJson;

public class GateUrl {

	public static String getGateUrl() {
		
		Node node = ServerJson.getRoot();
		node = node.get("gate");
		
		int port = node.getInt("port");
		String ip = node.getString("ip");
		String gateUrl = "http://" + ip + ":" + port;
		return gateUrl;
	}
}
