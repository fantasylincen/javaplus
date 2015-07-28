package com.google.common.collect;

import java.util.ArrayList;
import java.util.List;

public class Lists {

	public static <T> List<T> newArrayList() {
		return new ArrayList<T>();
	}

	public static <T> List<T> newArrayList(List<T> grids) {
		return new ArrayList<T>(grids);
	}

	public static <T> List<T> newArrayList(T[] fs) {
		List<T> ls = newArrayList();
		for (T t : fs) {
			ls.add(t);
		}
		return ls;
	}

}
