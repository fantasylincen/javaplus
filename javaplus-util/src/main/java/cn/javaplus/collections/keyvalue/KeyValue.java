package cn.javaplus.collections.keyvalue;


/**
 * 键值对
 * @author 林岑
 *
 * @param <K>
 * @param <V>
 */
public interface KeyValue<K, V> {

	/**
	 * 键
	 * @return
	 */
	K getKey();

	/**
	 * 值
	 * @return
	 */
	V getValue();

}
