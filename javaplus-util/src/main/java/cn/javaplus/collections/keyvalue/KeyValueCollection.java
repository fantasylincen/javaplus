package cn.javaplus.collections.keyvalue;

import java.util.Set;

/**
 * 该容器用于存放键值对，同时可以通过键得到值，也可以通过值返取键
 *
 * 比如你可以在容器中放入：
 *
 * 			001:"张"
 * 			002:"李"
 * 			003:"赵"
 *
 * 那么:
 *
 * 			get(001)返回：
 * 				"张"
 *
 * 			get("赵")返回：
 * 				003
 *
 * 那么:
 *
 * 			remove(001)之后， 容器内容变为：
 *
 * 				002:"李"
 * 				003:"赵"
 *
 * 			remove("赵")之后，容器变为：
 *
 * 				001:"张"
 * 				002:"李"
 *
 *
 * @author 林岑
 * @since	2013年5月26日 18:29:55
 *
 */
public interface KeyValueCollection<K, V extends Value> {

	/**
	 * 放入一个键值对
	 * @param k	键
	 * @param v 值
	 */
	void put(K k, V v);

	/**
	 * 通过键的值
	 * @param k	键
	 * @return	值
	 */
	V get(K k);

	/**
	 * 通过值得键
	 * @param v	值
	 * @return	键
	 */
	K get(V v);

	/**
	 * 通过键 移除键值对
	 * @param k	键
	 */
	V remove(K k);

	/**
	 * 通过值 把键值对都移除
	 * @param v	值
	 */
	K remove(V v);

	long size();

	/**
	 * 键值集
	 * @return
	 */
	Set<KeyValue<K, V>> entrySet();
}
