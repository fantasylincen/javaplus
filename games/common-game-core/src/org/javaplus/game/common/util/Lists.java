package org.javaplus.game.common.util;

import java.util.ArrayList;
import java.util.Collection;

public class Lists {

	public static <T> ArrayList<T> newArrayList(T... files) {
		ArrayList<T> ls = new ArrayList<T>();
		for (T t : files) {
			ls.add(t);
		}
		return ls;
	}

	public static <T> ArrayList<T> newArrayList(Collection<T> files) {
		ArrayList<T> ls = new ArrayList<T>();
		for (T t : files) {
			ls.add(t);
		}
		return ls;
	}

	public static <T> ArrayList<T> newArrayList() {
		ArrayList<T> ls = new ArrayList<T>();
		return ls;
	}

}
