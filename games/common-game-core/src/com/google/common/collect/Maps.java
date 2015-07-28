package com.google.common.collect;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {

	public static <K, V> Map<K, V> newConcurrentMap() {
		return new ConcurrentHashMap<K, V>();
	}

	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
}
