package cn.javaplus.collections.map;

import java.util.Collection;

public interface DoubleKeyMap<K1, K2, V> {

	/**
	 * 指定两个键放入一个值
	 *
	 * @param k1
	 * @param k2
	 * @param v
	 */
	public abstract void put(K1 k1, K2 k2, V v);

	/**
	 * 通过两个键, 返回对应值
	 *
	 * @param k1
	 * @param k2
	 * @return
	 */
	public abstract V get(K1 k1, K2 k2);

	public abstract void clear();

	public abstract Collection<DoubleKey<K1,K2>> keySet();

	public abstract Collection<V> values();

	public abstract V remove(K1 k1, K2 k2);

}