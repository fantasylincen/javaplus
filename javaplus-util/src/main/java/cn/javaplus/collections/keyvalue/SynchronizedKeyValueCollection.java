package cn.javaplus.collections.keyvalue;

import java.util.Set;



/**
 * @Deprecated 用SynchronizedKeyValueCollection2取代
 * @author 林岑
 *
 * @param <K>
 * @param <V>
 */
public class SynchronizedKeyValueCollection<K, V extends Value> implements KeyValueCollection<K, V>{


	private KeyValueCollection<K, V> collection;

	public SynchronizedKeyValueCollection() {
		collection = new KeyValueCollectionImpl<K, V>();
	}

	@Override
	public synchronized void put(K k, V v) {
		collection.put(k, v);
	}

	@Override
	public synchronized V get(K k) {
		return collection.get(k);
	}

	@Override
	public synchronized K get(V v) {
		return collection.get(v);
	}

	@Override
	public synchronized V remove(K k) {
		return collection.remove(k);
	}

	@Override
	public synchronized K remove(V v) {
		return collection.remove(v);
	}

	@Override
	public synchronized String toString() {
		return collection + "";
	}

	@Override
	public long size() {
		return collection.size();
	}

	@Override
	public Set<KeyValue<K, V>> entrySet() {
		return collection.entrySet();
	}
}
