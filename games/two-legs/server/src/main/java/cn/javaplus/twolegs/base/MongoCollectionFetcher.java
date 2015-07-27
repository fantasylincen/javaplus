package cn.javaplus.twolegs.base;

import cn.javaplus.twolegs.mongo.MongoGen.CollectionFetcher;

import com.mongodb.DBCollection;

public class MongoCollectionFetcher implements CollectionFetcher {

	public DBCollection getCollection(String collectionName) {
		return CollectionFactory.getCollection(collectionName);
	}

}
