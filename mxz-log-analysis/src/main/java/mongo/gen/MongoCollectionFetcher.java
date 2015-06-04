package mongo.gen;

import cn.javaplus.db.mongo.CollectionFetcher;

import com.mongodb.DBCollection;

public class MongoCollectionFetcher implements CollectionFetcher {

	public DBCollection getCollection(String collectionName) {
		return CollectionFactory.getCollection(collectionName);
	}

}
