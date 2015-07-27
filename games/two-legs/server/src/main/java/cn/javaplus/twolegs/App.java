package cn.javaplus.twolegs;

import cn.javaplus.log.Log;
import cn.javaplus.twolegs.base.GameServer;
import cn.javaplus.twolegs.base.MongoCollectionFetcher;
import cn.javaplus.twolegs.ipmonitor.GameServerIpMonitor;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.nick.ServerStartImportNicks;
import cn.javaplus.twolegs.rankinglist.RankingList;
import cn.javaplus.util.MainArgs;

/**
 * Hello world!
 *
 */
public class App {

	private static GameServer gs;
	private static GameServerIpMonitor gsm;

	private static final class StopListenerImpl implements StopListener {

		@SuppressWarnings("deprecation")
		@Override
		public void onStop() {
			gs.stop();
			gsm.stop();
			Log.d("Server stop by game manager!");
		}
	}

	public static void main(String[] args) {
		init(args);

//		StopListenerImpl listener = new StopListenerImpl();
//		StopServerMonitor monitor = new StopServerMonitor(listener);
		if (MainArgs.contains("stop")) {
//			monitor.sendStopRequest();
		} else {
			gs = new GameServer();
			gsm = new GameServerIpMonitor();
			gs.start();
			gsm.start();
//			monitor.start();
		}
		RankingList.getInstance().loadUsers();
		new ServerStartImportNicks().importNicks();
		Log.d("服务器启动成功");
	}

	private static void init(String[] args) {
		MainArgs.set(args);
		MongoCollectionFetcher fetcher = new MongoCollectionFetcher();
		Daos.setCollectionFetcher(fetcher);
	}
}
