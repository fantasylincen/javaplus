package cn.javaplus.crazy.server;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.mongo.Daos;

public class InitMongoCollection implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		ServerMongoCollectionFetcher fetcher = new ServerMongoCollectionFetcher();
		Daos.setCollectionFetcher(fetcher);
	}

}
