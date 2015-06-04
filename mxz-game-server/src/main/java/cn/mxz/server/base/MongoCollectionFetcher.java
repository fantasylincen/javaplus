package cn.mxz.server.base;

import cn.mxz.server.mongo.MongoGen.CollectionFetcher;

import com.mongodb.DBCollection;

public class MongoCollectionFetcher implements CollectionFetcher {

	public DBCollection getCollection(String collectionName) {
		return CollectionFactory.getCollection(collectionName);
	}

}
