package cn.javaplus.crazy.base;

import cn.javaplus.db.mongo.CollectionFetcher;

import com.mongodb.DBCollection;

public class GateMongoCollectionFetcher implements CollectionFetcher {

	@Override
	public DBCollection getCollection(String collectionName) {
		return GateCollectionFactory.getCollection(collectionName);
	}

}
