package cn.javaplus.cache;

import java.util.Collection;
import java.util.LinkedHashMap;

public class CacheImpl<K, V> implements Cache<K, V> {

	LinkedHashMap<K, V> c = new LinkedHashMap<K, V>();
	
	@Override
	public synchronized Collection<V> values() {
		return c.values();
	}

	@Override
	public synchronized void clear() {
		c.clear();
	}

	@Override
	public synchronized V remove(K key) {
		return c.remove(key);
	}

	@Override
	public synchronized void put(K k, V v) {
		c.put(k, v);
	}

	@Override
	public synchronized V get(K key) {
		return c.get(key);
	}

	@Override
	public synchronized int size() {
		return c.size();
	}
	
	public static void main(String[] args) {

//		Builder<Integer, Integer> builder = new ConcurrentLinkedHashMap.Builder<Integer, Integer>();
//		builder.initialCapacity(0);
//		builder.maximumWeightedCapacity(100000);
//		ConcurrentLinkedHashMap<Integer, Integer> c = builder.build();
		
		LinkedHashMap<Integer, Integer> c = new LinkedHashMap<Integer, Integer>();
		
		c.put(1, 1);
		c.put(4, 4);
		c.put(3, 3);
		c.put(2, 2);
		System.out.println(c.values());
	}

}
