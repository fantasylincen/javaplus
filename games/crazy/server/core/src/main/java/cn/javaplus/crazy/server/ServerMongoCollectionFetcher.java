package cn.javaplus.crazy.server;

import cn.javaplus.db.mongo.CollectionFetcher;

import com.mongodb.DBCollection;

public class ServerMongoCollectionFetcher implements CollectionFetcher {

	@Override
	public DBCollection getCollection(String collectionName) {
		return ServerCollectionFactory.getCollection(collectionName);
	}

}
