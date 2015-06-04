package cn.javaplus.collections.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 只读列表
 * @author 	林岑
 * @time		2013年5月4日 21:01:14
 *
 * @param <V>
 */
public class ListReadOnly<V> implements Iterable<V>{
	
	private List<V> list;

	public ListReadOnly(List<V> list) {
		this.list = list;
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public V get(int index) {
		return list.get(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public int size() {
		return list.size();
	}

	@Override
	public Iterator<V> iterator() {
		return list.iterator();
	}
	
	
}
