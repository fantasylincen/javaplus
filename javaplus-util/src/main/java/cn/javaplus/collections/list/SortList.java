package cn.javaplus.collections.list;

import java.util.Collections;
import java.util.List;

/**
 * 一个在加入列表的时候自动排序的列表, 可以设置列表上限
 */
public class SortList<T extends Comparable<T>> {

	private List<T> list = Lists.newArrayList();

	private int limit;

	public SortList(int limit) {
		this.limit = limit;
		if (this.limit < 0) {
			String s = "limit must >= 0, limit = " + limit;
			throw new IllegalArgumentException(s);
		}
	}

	public void add(T t) {
		list.add(t);
		Collections.sort(list);
		removeLasts();
	}

	private void removeLasts() {
		while (list.size() > limit) {
			list.remove(list.size() - 1);
		}
	}

	public List<T> values() {
		return list;
	}
}
