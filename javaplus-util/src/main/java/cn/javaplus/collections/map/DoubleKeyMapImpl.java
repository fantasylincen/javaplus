package cn.javaplus.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DoubleKeyMapImpl<K1, K2, V> implements DoubleKeyMap<K1, K2, V>{

	private Map<DoubleKey<K1, K2>, V>	map = new ConcurrentHashMap<DoubleKey<K1, K2>, V>();

	@Override
	public void put(K1 k1, K2 k2, V v) {

		DoubleKey<K1, K2> key = buildKey(k1, k2);

		map.put(key, v);
	}

	private DoubleKey<K1, K2> buildKey(K1 k1, K2 k2) {

		return new DoubleKeyImpl<K1, K2>(k1, k2);
	}

	@Override
	public V get(K1 k1, K2 k2) {

		DoubleKey<K1, K2> key = buildKey(k1, k2);

		return map.get(key);
	}

	@Override
	public void clear() {

		map.clear();
	}

	@Override
	public Collection<DoubleKey<K1,K2>> keySet() {

		Set<DoubleKey<K1,K2>> keySet = map.keySet();

		return keySet;
	}

	@Override
	public Collection<V> values() {

		return map.values();
	}

	@Override
	public V remove(K1 k1, K2 k2) {

		DoubleKey<K1, K2> key = buildKey(k1, k2);

		V remove = map.remove(key);

		return remove;
	}

}
