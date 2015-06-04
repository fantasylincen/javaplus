package cn.javaplus.file;

import java.util.Set;

/**
 * 键值数据库
 * @author 林岑
 *
 */
public interface KeyValueDB {

	/**
	 * 查询
	 * @param key
	 * @return
	 */
	String get(Object key);

	/**
	 * 添加
	 * @param key
	 * @param value
	 */
	void add(Object key, String value);

	/**
	 * 删除
	 * @param key
	 */
	void delete(Object key);

	/**
	 * 更新
	 * @param key
	 * @param value
	 */
	void update(Object key, String value);

	/**
	 * 所有键
	 * @return
	 */
	Set<Object> keySet();
}
