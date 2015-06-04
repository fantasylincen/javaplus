package cn.javaplus.collections.keyvalue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author 林岑
 *
 * @param <K>
 * @param <V>
 */
public class KeyValueCollectionImpl<K, V extends Value> implements KeyValueCollection<K, V>{

	private Map<K, V> map1;

	private Map<V, K> map2;

	public KeyValueCollectionImpl() {

		map1 = new HashMap<K, V>();

		map2 = new HashMap<V, K>();
	}

	@Override
	public void put(K k, V v) {

		if(get(k) != null) {

			throw new KeyExistException();
		}

		if(get(v) != null) {

			throw new ValueExistException();
		}

		map1.put(k, v);

		map2.put(v, k);
	}

	@Override
	public V get(K k) {
		return map1.get(k);
	}

	@Override
	public K get(V v) {
		return map2.get(v);
	}

	@Override
	public V remove(K k) {

		V v = map1.remove(k);

		map2.remove(v);

		return v;
	}

	@Override
	public K remove(V v) {

		K k = map2.remove(v);

		map1.remove(k);

		return k;
	}

	@Override
	public String toString() {

		return map1.toString() + map2.toString();
	}

	@Override
	public long size() {

		return map1.size();
	}

	@Override
	public Set<KeyValue<K, V>> entrySet() {

		Set<Entry<K, V>> ss = map1.entrySet();

		Set<KeyValue<K, V>> set = new HashSet<KeyValue<K, V>>();

		for (Entry<K, V> entry : ss) {

			set.add(new KeyValueImpl<K, V>(entry.getKey(), entry.getValue()));
		}

		return set;
	}
}
