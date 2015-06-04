package cn.javaplus.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Maps {

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> Map<K, V> newTreeMap() {
		return new TreeMap<K, V>();
	}

	public static <K, V> HashMap<K, V> newHashMap(Map<K, V> m) {
		return new HashMap<K, V>(m);
	}

	public static <V> Map<String, V> newStringHashMap() {
		return new StringHashMap<V>();
	}

	public static <V> Map<Integer, V> newIntegerHashMap() {
		return new IntegerHashMap<V>();
	}

}
