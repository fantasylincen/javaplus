package cn.javaplus.collections.queue;

import java.util.Collections;

/**
 * 长度被限制 ,同时自动排序的列表
 * @author 林岑
 *
 * @param <V>
 */
@SuppressWarnings("serial")
public class RestrictListSorted<V extends Comparable<V>> extends RestrictList<V> {

	public RestrictListSorted(int maxCount) {
		super(maxCount);
	}

	@Override
	public V append(V value) {
		add(value);
		
		Collections.sort(this);
		
		V poll = null;
		while(size() > maxCount) {
			poll = remove(0);
		}
		return poll;
	}
}
