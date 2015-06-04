package cn.javaplus.collections.keyvalue;
//package lincen.javase.util.collections.keyvalue;
//
//
//
//public class SynchronizedKeyValueCollection2<K, V extends Value> implements KeyValueCollection<K, V>{
//
//
//	private KeyValueCollection<K, V> collection;
//
//	public SynchronizedKeyValueCollection2() {
//		collection = new KeyValueCollectionImpl2<>();
//	}
//
//	@Override
//	public synchronized void put(K k, V v) {
//		collection.put(k, v);
//	}
//
//	@Override
//	public synchronized V get(K k) {
//		return collection.get(k);
//	}
//
//	@Override
//	public synchronized K get(V v) {
//		return collection.get(v);
//	}
//
//	@Override
//	public synchronized V remove(K k) {
//		return collection.remove(k);
//	}
//
//	@Override
//	public synchronized K remove(V v) {
//		return collection.remove(v);
//	}
//
//	@Override
//	public synchronized String toString() {
//		return collection + "";
//	}
//
//	@Override
//	public long size() {
//		return collection.size();
//	}
//}
