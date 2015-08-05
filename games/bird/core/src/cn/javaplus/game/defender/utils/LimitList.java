package cn.javaplus.game.defender.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cn.javaplus.game.defender.exceptions.UnableException;

/**
 * 带容量上线的列表， 如果超过上限，优先移除之前加的元素  保证容量不超过上限
 * @author Administrator
 *
 * @param <T>
 */
public class LimitList<T> implements List<T> {
	private List<T> list = new ArrayList<T>();
	private int limit;

	public LimitList(int limit) {
		this.limit = limit;
	}

	public void add(int index, T element) {
		throw new UnableException();
	}

	public boolean add(T e) {
		boolean add = list.add(e);
		if(list.size() > limit) {
			list.remove(0);
		}
		return add;
	}

	public boolean addAll(Collection<? extends T> c) {
		boolean add = false;
		for (T t : c) {
			add = add(t);
		}
		return add;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnableException();
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public T get(int index) {
		return list.get(index);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator<T> iterator() {
		return list.iterator();
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	public T remove(int index) {
		return list.remove(index);
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public T set(int index, T element) {
		return list.set(index, element);
	}

	public int size() {
		return list.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	
}
