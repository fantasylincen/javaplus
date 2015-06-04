package cn.javaplus.collections.counter;

import java.util.Map.Entry;
import java.util.Set;


/**
 * 计数器
 * @author 林岑
 *
 * @param <K>	该计数器的键的类型
 */
public interface ICounter<K> {
	
	/**
	 * 获得key对应的数量, 如果在之前没有key对应的数量, 默认视为0
	 * @param key	键
	 * @return		数量
	 */
	public int get(K key);
	
	/**
	 * 将key对应的数量增加1, 如果在之前没有key对应的数量, 默认视为0
	 * @param key	键
	 * @return		增加后的值
	 */
	public int add(K key);
	
	/**
	 * 设置key所对应的数量
	 * @param key		键
	 * @param count		数量
	 * @return			设置前的数量
	 */
	public int set(K key, int count);
	
	/**
	 * 让key所对应的数量增加count
	 * @param key		键
	 * @param count		增量
	 * @return			增加后的数量
	 */
	public int add(K key, int count);

	/**
	 * 获取所有键值对
	 * @return
	 */
	public Set<Entry<K, Integer>> entrySet();
	
	/**
	 * 计数器键的数量
	 * @return
	 */
	public int size() ;
	
	/**
	 * 键集
	 * @return
	 */
	public Set<K> keySet();
	
	/**
	 * 清空计数器
	 */
	public void clear();
}
