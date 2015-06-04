package cn.javaplus.cache;

import java.util.Collection;

public interface Cache<K, V> {

	Collection<V> values();

	void clear();

	V remove(K key);

	void put(K k, V v);

	V get(K key);

	int size();

}
