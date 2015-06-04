package cn.mxz.system;

import cn.javaplus.db.KeyValueCollection;
import cn.mxz.util.db.KeyValueCollectionFactory;

/**
 * 系统级计数器
 * @author 林岑
 *
 */
public class SystemCounter {

	private static SystemCounter	instance;

	private SystemCounter() {
	}

	public static final SystemCounter getInstance() {
		if (instance == null) {
			instance = new SystemCounter();
		}
		return instance;
	}

	/**
	 * 增加某个键 对应的值
	 * @param key
	 * @param count
	 */
	public void add(SystemCounterKey key, int count) {
		int old = get(key);
		set(key, count + old);
	}

	/**
	 * 设置某个键 对应的值
	 * @param key
	 * @param count
	 */
	public void set(SystemCounterKey key, int count) {
		KeyValueCollection<Object, String> c = KeyValueCollectionFactory.getMongoCollection();
		c.put(key, count + "");
	}

	/**
	 * 获取某个键 对应的值
	 * @param key
	 */
	public int get(SystemCounterKey key) {
		KeyValueCollection<Object, String> c = KeyValueCollectionFactory.getMongoCollection();
		String cnt = c.get(key);
		if (cnt == null) {
			cnt = "0";
			c.put(key, cnt);
		}

		return new Integer(cnt);
	}
}
