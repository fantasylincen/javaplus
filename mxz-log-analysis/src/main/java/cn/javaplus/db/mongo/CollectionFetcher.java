package cn.javaplus.db.mongo;

import com.mongodb.DBCollection;

public interface CollectionFetcher {

	DBCollection getCollection(String string);

}
