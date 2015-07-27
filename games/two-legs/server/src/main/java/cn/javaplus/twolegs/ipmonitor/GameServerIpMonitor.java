package cn.javaplus.twolegs.ipmonitor;

import cn.javaplus.log.Log;
import cn.javaplus.twolegs.json.Node;
import cn.javaplus.twolegs.json.ServerJson;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;

public class GameServerIpMonitor extends Thread {

	@Override
	public void run() {
		while (true) {
			try {
				String ip = Util.IP.getMyIp();
				uploadIp(ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Util.Thread.sleep(2 * Time.MILES_ONE_MIN);
		}
	}

	private void uploadIp(String ip) {
		Node node = ServerJson.getRoot();
		node = node.get("server");
		node.set("ip", ip);
		node.commit("two-legs", "server.json");
		Log.d("Commit success!");
	}
}
