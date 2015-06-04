package cn.javaplus.collections;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;

public class SortedMapList<K, V extends Comparable<V>> {

	private Map<K, V> map = Maps.newHashMap();
	private List<V> list = Lists.newArrayList();

	public synchronized void put(K k, V v) {
		map.put(k, v);
		list.add(v);
	}

	public synchronized void sort() {
		Collections.sort(list);
	}

	/**
	 * 只读列表, 不要去修改列表内容
	 * @return
	 */
	public List<V> list() {
		return Lists.newArrayList(list);
	}
	/**
	 * 只读的map, 不要修改图的内容
	 */
	public Map<K, V> map() {
		return Maps.newHashMap(map);
	}

	public int size() {
		return list.size();
	}
}
