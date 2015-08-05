package cn.javaplus.shhz.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {

	public static <K, V> Map<K, V> newConcurrentMap() {
		return new ConcurrentHashMap<K, V>();
	}

}
