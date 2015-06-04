package db.dao.impl;


/**
 * 缓存对象
 * @author 林岑
 *
 * @param <T>
 */
public class CacheObject<T> {

	private T	object;

	private long	createTime;

	public CacheObject(T object) {

		this.object = object;

		createTime = System.currentTimeMillis();
	}

	public T getObject() {
		return object;
	}

	/**
	 * 缓存生存事件
	 * @return
	 */
	public long getAliveTime() {

		return System.currentTimeMillis() - createTime;
	}

	/**
	 * 增长缓存对象的生命周期
	 */
	public void addLiveTime() {

		createTime = System.currentTimeMillis();
	}

}