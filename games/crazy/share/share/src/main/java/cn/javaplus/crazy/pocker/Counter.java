package cn.javaplus.crazy.pocker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Counter<K> {

	private Map<K, Integer> counter = new HashMap<K, Integer>();

	public int get(K key) {

		Integer count = this.counter.get(key);

		if (count == null) {

			count = 0;

			this.counter.put(key, count);
		}

		return count;
	}

	public int add(K key) {
		return this.add(key, 1);
	}

	public int set(K key, int count) {

		int countOld = this.get(key);

		this.counter.put(key, count);

		return countOld;
	}

	public int add(K key, int count) {

		int countNew = this.get(key) + count;

		this.counter.put(key, countNew);

		return countNew;
	}

	public void clear() {
		this.counter.clear();
	}

	public Set<Entry<K, Integer>> entrySet() {
		return this.counter.entrySet();
	}

	public int size() {
		return keySet().size();
	}

	public Set<K> keySet() {
		return counter.keySet();
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("[");

		for (Entry<K, Integer> e : entrySet()) {

			sb.append(e.getKey() + "-" + e.getValue() + " ");
		}

		sb.append("]");

		return sb.toString();
	}
}
