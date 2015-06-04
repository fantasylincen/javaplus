package cn.mxz.util.db;

import cn.javaplus.db.KeyValueCollection;


public class KeyValueCollectionFactory {

	public static KeyValueCollection<Object, String> getMongoCollection() {
		return new MongoKeyValueCollection();
	}

}
