package cn.javaplus.collections.list;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.javaplus.collections.set.Sets;

/**
 * 一个在加入列表的时候自动排序的列表, 可以设置列表上限
 */
public class ConcurrentSortList<T extends Comparable<T>> {

	private Collection<T> set;

	private int limit;

	public ConcurrentSortList(int limit) {
		set = Sets.newConcurrentHashSet();
		this.limit = limit;
		if (this.limit < 0) {
			String s = "limit must >= 0, limit = " + limit;
			throw new IllegalArgumentException(s);
		}
	}

	public void add(T t) {
		set.add(t);
		removeLasts();
	}

	private void removeLasts() {
		List<T> ls = values();
		while (set.size() > limit) {
			T t = ls.get(ls.size() - 1);
			set.remove(t);
		}
	}

	public List<T> values() {
		List<T> ls = Lists.newArrayList(set);
		Collections.sort(ls);
		return ls;
	}
	
	@Override
	public String toString() {
		return values().toString();
	}
}
