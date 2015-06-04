package cn.javaplus.mxzrobot.db;

import mongo.gen.MongoGen.CollectionFetcher;

import com.mongodb.DBCollection;

public class MongoCollectionFetcher implements CollectionFetcher {

	public DBCollection getCollection(String collectionName) {
		return CollectionFactory.getCollection(collectionName);
	}

}
