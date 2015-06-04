package cn.javaplus.collections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListReadOnly<T> implements List<T> {
	private List<T>	arrayList	= new ArrayList<T>();

	public ArrayListReadOnly(List<T> arrayList) {
		this.arrayList = arrayList;
	}

	@Override
	public int size() {
		return arrayList.size();
	}

	@Override
	public boolean isEmpty() {
		return arrayList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return arrayList.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return new ReadOnlyIterator<T>(arrayList.iterator());
	}

	@Override
	public Object[] toArray() {
		return arrayList.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return arrayList.toArray(a);
	}

	@Override
	public boolean add(T e) {
		throw new ReadOnlyException();
	}

	@Override
	public boolean remove(Object o) {
		throw new ReadOnlyException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return arrayList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new ReadOnlyException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new ReadOnlyException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new ReadOnlyException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new ReadOnlyException();
	}

	@Override
	public void clear() {
		throw new ReadOnlyException();
	}

	@Override
	public boolean equals(Object o) {
		return arrayList.equals(o);
	}

	@Override
	public int hashCode() {
		return arrayList.hashCode();
	}

	@Override
	public T get(int index) {
		return arrayList.get(index);
	}

	@Override
	public T set(int index, T element) {
		throw new ReadOnlyException();
	}

	@Override
	public void add(int index, T element) {
		throw new ReadOnlyException();
	}

	@Override
	public T remove(int index) {
		throw new ReadOnlyException();
	}

	@Override
	public int indexOf(Object o) {
		return arrayList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return arrayList.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return arrayList.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return arrayList.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return arrayList.subList(fromIndex, toIndex);
	}

}
