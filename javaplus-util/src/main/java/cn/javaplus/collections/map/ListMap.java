package cn.javaplus.collections.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListMap<K, V> implements Iterable<K>{

	private Map<K, List<V>> map = Maps.newConcurrentMap();

	/**
	 * 该方法不可能返回空, 如果是第一次调用该方法, 那么会返回一个空表
	 * @param key
	 * @return
	 */
	public List<V> get(K key) {
		List<V> ls = map.get(key);
		if(ls == null) {
			ls = new ArrayList<V>();
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
		List<V> ls = get(key);
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
	public List<V> remove(K key) {
		return map.remove(key);
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Iterator<K> iterator() {
		return map.keySet().iterator();
	}

	public void clear() {

		map.clear();
	}
}
