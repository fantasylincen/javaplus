package cn.javaplus.collections.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetMap<K, V> {
	
	private Map<K, Set<V>> map = new HashMap<K, Set<V>>();
	
	/**
	 * 该方法不可能返回空, 如果是第一次调用该方法, 那么会返回一个空表
	 * @param key
	 * @return
	 */
	public Set<V> get(K key) {
		Set<V> ls = map.get(key);
		if(ls == null) {
			ls = new HashSet<V>();
			map.put(key, ls);
		}
		return ls;
	}
	
	/**
	 * 给指定用户添加一条记录
	 * @param key
	 * @param value
	 * @return
	 */
	public void add(K key, V value) {
		get(key).add(value);
	}
	
	/**
	 * 给指定元素添加一条记录,如果之前已经包含了该记录,那会添加失败,同时返回false
	 * @param key
	 * @param value
	 * @return	是否添加成功
	 */
	public boolean put(K key, V value) {
		Set<V> ls = get(key);
		if(ls.contains(value)) {
			return false;
		} else {
			ls.add(value);
			return true;
		}
	}
	
	/**
	 * 移除指定元素的所有列表
	 * @param key
	 * @return
	 */
	public Set<V> remove(K key) {
		return map.remove(key);
	}

	public Set<K> keySet() {
		return map.keySet();
	}
}