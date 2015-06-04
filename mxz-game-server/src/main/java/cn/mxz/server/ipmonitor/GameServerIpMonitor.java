package cn.mxz.server.ipmonitor;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;
import cn.mxz.server.json.Node;
import cn.mxz.server.json.ServerJson;

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
			Util.Thread.sleep(3 * Time.MILES_ONE_MIN);
		}
	}

	private void uploadIp(String ip) {
		Node node = ServerJson.getRoot();
		node = node.get("server");
		node.set("ip", ip);
		node.commit("game-configs", "game1001.json");
		Log.d("Commit success!");
	}
}
