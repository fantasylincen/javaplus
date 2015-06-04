package cn.javaplus.collections.list;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public class Lists {

	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}

	public static <T> List<T> newArrayList(Collection<T> ls) {
		return new ArrayList<T>(ls);
	}

	public static <T> List<T> newArrayList(T... ls) {
		ArrayList<T> a = new ArrayList<T>();
		for (T t : ls) {
			a.add(t);
		}
		return a;
	}

}
