package db.dao.impl;



public interface Cache2<K1, K2, T> extends CacheBase<T> {

	void put(K1 k1, K2 k2, T afbo);

	void delete(K1 k1, K2 k2);

	T get(K1 k1, K2 k2);
}
