package cn.javaplus.crazy.ipmonitor;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;

public class GateIpMonitor extends Thread {

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
		node = node.get("gate");
		node.set("ip", ip);
		node.commit("crazy-pocker", "config.json");
	}
}
