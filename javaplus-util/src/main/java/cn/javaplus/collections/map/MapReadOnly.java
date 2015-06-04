package cn.javaplus.collections.map;

import java.util.Collection;
import java.util.Map;

/**
 * 一个只读的字典, 线程
 * @author 	林岑
 * @time		2013年5月4日 20:56:04
 *
 * @param <K>
 * @param <V>
 */
public class MapReadOnly<K, V> {
	
	private Map<K, V> map;

	public MapReadOnly(Map<K, V> map) {
		this.map = map;
	}
	
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public V get(Object key) {
		return map.get(key);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public int size() {
		return map.size();
	}

	public Collection<V> values() {
		return map.values();
	}

	public void remove(K k) {
		map.remove(k);
	}
	
	
	
}
