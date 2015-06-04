package cn.mxz.server;

import cn.javaplus.log.Log;
import cn.javaplus.util.MainArgs;
import cn.javaplus.util.Util;
import cn.mxz.server.base.GameServer;
import cn.mxz.server.base.MongoCollectionFetcher;
import cn.mxz.server.ipmonitor.GameServerIpMonitor;
import cn.mxz.server.mongo.MongoGen.Daos;
import cn.mxz.server.mongo.MongoGen.SystemKeyValueDao;
import cn.mxz.server.mongo.MongoGen.SystemKeyValueDto;

/**
 * Hello world!
 *
 */
public class App {

	private static GameServer gs;
	private static GameServerIpMonitor gm;

	private static final class StopListenerImpl implements StopListener {

		@SuppressWarnings("deprecation")
		@Override
		public void onStop() {
			gs.stop();
			gm.stop();
			Log.d("Server stop by game manager!");
		}
	}

	public static void main(String[] args) {
		init(args);

		StopListenerImpl listener = new StopListenerImpl();
		StopServerMonitor monitor = new StopServerMonitor(listener);
		if (MainArgs.contains("stop")) {
			monitor.sendStopRequest();
		} else {
			gs = new GameServer();
			gm = new GameServerIpMonitor();
			gs.start();
			gm.start();
			monitor.start();
		}
	}

	private static void init(String[] args) {
		MainArgs.set(args);
		MongoCollectionFetcher fetcher = new MongoCollectionFetcher();
		Daos.setCollectionFetcher(fetcher);
	}
}
