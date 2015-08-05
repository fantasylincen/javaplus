package cn.javaplus.crazy.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Sets {

	public static <T> Set<T> newHashSet(T... t) {
		HashSet<T> set = new HashSet<T>();
		for (T t2 : t) {
			set.add(t2);
		}
		return set;
	}

	public static <T> Set<T> newHashSet(Collection<T> t) {
		HashSet<T> set = new HashSet<T>();
		for (T t2 : t) {
			set.add(t2);
		}
		return set;
	}
}
