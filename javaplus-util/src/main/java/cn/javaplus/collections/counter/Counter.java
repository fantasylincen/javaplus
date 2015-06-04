package cn.javaplus.collections.counter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Counter<K> implements ICounter<K>{

	private Map<K, Integer> counter = new HashMap<K, Integer>();
	
	@Override
	public int get(K key) {
		
		Integer count = this.counter.get(key);
		
		if(count == null) {
			
			count = 0;
			
			this.counter.put(key, count);
		}
		
		return count;
	}

	@Override
	public int add(K key) {
		return this.add(key, 1);
	}

	@Override
	public int set(K key, int count) {
		
		int countOld = this.get(key);
		
		this.counter.put(key, count);
		
		return countOld;
	}

	@Override
	public int add(K key, int count) {
		
		int countNew = this.get(key) + count;
		
		this.counter.put(key, countNew);
		
		return countNew;
	}
	
	public static void main(String[] args) {
		
		ICounter<String> counter = new Counter<String>();
		
		String userName = "Tom";
		
		for (int i = 0; i < 1000; i++) {
			
			System.out.println(counter.get(userName));
			
			counter.add(userName);
		}
	}

	@Override
	public void clear() {
		this.counter.clear();
	}

	@Override
	public Set<Entry<K, Integer>> entrySet() {
		return this.counter.entrySet();
	}

	@Override
	public int size() {
		return keySet().size();
	}

	@Override
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
