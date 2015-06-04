package cn.javaplus.db;

/**
 * 键值容器
 * @author 林岑
 *
 */
public interface KeyValueCollection<K, V> {

	/**
	 * 获取值(内部根据o.toString()获取值)
	 *
	 * @param k
	 * @return 如果没有其值, 返回null
	 */
	V get(K k);

	/**
	 * 跟Map.put 功能完全一致
	 * @param k
	 * @param v
	 */
	void put(K k, V v);
}
