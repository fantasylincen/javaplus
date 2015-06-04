package db.dao.impl;

import java.util.List;

import cn.javaplus.time.Time;

public interface CacheBase<T> {

	/** 缓存生存时间(毫秒) */
	long	ALIVE_TIME	= Time.MILES_ONE_HOUR * 5;

	List<T> getAll(int pageNo, int pageSize);

	List<T> getAll();

	void clear();

}