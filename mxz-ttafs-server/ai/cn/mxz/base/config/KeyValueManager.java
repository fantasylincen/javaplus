package cn.mxz.base.config;

/**
 * 键值管理器
 * @author 林岑
 *
 */
public interface KeyValueManager {

	/**
	 * 获得某个键对应的值 (数据库实际以 key + args...toString.. 作为主键)
	 *
	 * @param key
	 *
	 * @param args
	 *
	 * @return	如果不存在这个值, 则根据key的默认值进行初始化, 不会返回空值
	 */
	String get(KeyValueDefine key, Object... args);

	/**
	 *
	 *
	 * 设置某个键的值  (数据库实际以 key + args...toString.. 作为主键)
	 *
	 * @param key
	 *
	 * @param o
	 *
	 * @param args
	 */
	void put(KeyValueDefine key, Object o, Object... args);

}
