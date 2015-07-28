package cn.javaplus.crazy.ipmonitor;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.NodeArray;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.util.MainArgs;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;

public class ServerIpMonitor extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				String ip = Util.IP.getMyIp();
				uploadIp(ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Util.Thread.sleep(3 * Time.MILES_ONE_MIN);
		}
	}

	private void uploadIp(String ip) {
		Node node = ServerJson.getRoot();
		Integer serverId = MainArgs.findInteger("id");

		NodeArray arr = node.getArray("servers");
		Node n = arr.find("id", serverId);
		n.set("ip", ip);
		n.commit("crazy-pocker", "config.json");
	}
}
