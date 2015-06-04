package mongo.gen;

import com.mongodb.DBCollection;

public interface CollectionFetcher {

	/**
	 * 获得指定的容器对象
	 * @param collectionName
	 * @return
	 */
	DBCollection getCollection(String collectionName);
}
