package cn.mxz.base.server;

import mongo.gen.MongoGen.CollectionFetcher;

import com.mongodb.DBCollection;

import db.dao.factory.CollectionFactory;

public class MongoCollectionFetcher implements CollectionFetcher {

	@Override
	public DBCollection getCollection(String collectionName) {
		return CollectionFactory.getCollection(collectionName);
	}

}
