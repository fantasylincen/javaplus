package cn.javaplus.collections.set;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Sets {

	public static <T> Set<T> newHashSet() {
		return new HashSet<T>();
	}

	public static <T> Set<T> newHashSet(Collection<T> ls) {
		return new HashSet<T>(ls);
	}

	public static <T> Set<T> newHashSet(T... ls) {
		Set<T> s = new HashSet<T>();
		for (T t : ls) {
			s.add(t);
		}
		return s;
	}

	public static<T> Set<T> newConcurrentHashSet() {
		return Collections.newSetFromMap(new ConcurrentHashMap<T,Boolean>());
	}

}
