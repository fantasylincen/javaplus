package db.dao.impl;

import java.util.List;

import cn.javaplus.page.Page;

public abstract class AbstractCache<K1, T> {

	public AbstractCache() {

		super();
	}

	public List<T> getAll(int pageNo, int pageSize) {

		List<T> all = getAll();

		Page<T> page = new Page<T>(all, pageSize);

		return page.getPage(pageNo);
	}

	public abstract List<T> getAll();
}