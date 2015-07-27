package cn.javaplus.crazy.collections;

import java.util.ArrayList;
import java.util.Collection;

public class Lists {

	public static <T> ArrayList<T> newArrayList() {
		return new ArrayList<T>();
	}

	public static <T> ArrayList<T> newArrayList(Collection<T> grids) {
		return new ArrayList<T>(grids);
	}

	public static <T> ArrayList<T> newArrayList(T... fs) {
		ArrayList<T> ls = newArrayList();
		for (T t : fs) {
			ls.add(t);
		}
		return ls;
	}

}
