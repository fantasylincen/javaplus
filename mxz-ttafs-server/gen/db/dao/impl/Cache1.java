package db.dao.impl;



public interface Cache1<K1, T> extends CacheBase<T> {

	void put(K1 k1, T afbo);

	void delete(K1 k1);

	T get(K1 k1);
}
