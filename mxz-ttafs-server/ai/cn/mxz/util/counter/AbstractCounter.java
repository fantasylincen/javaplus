package cn.mxz.util.counter;

import cn.mxz.city.City;
import db.domain.DBCounter;

abstract class AbstractCounter<T extends DBCounter> implements UserCounter {

	protected City	city;

	AbstractCounter(City city) {

		this.city = city;
	}

	private synchronized T fromDB(CounterKey id, Object... args) {

		String key = key(id, args);

		CounterDao<T> DAO = getDao();

		T c = DAO.get(city.getId(), key);

		if (c == null) {

			c = DAO.createDTO();

			c.setUname(city.getId());

			c.setCounterId(key);

			DAO.save(c);
		}

		return c;
	}

	protected abstract CounterDao<T> getDao();

	private String key(CounterKey id, Object[] args) {
		String s = id.toString();
		for (Object object : args) {
			s += ":" + object;
		}
		return s;
	}

	@Override
	public void add(CounterKey id, int add, Object... args) {

		T c = fromDB(id, args);

		c.setCount(c.getCount() + add);

		getDao().save(c);
	}

	@Override
	public int get(CounterKey id, Object... args) {

		T c = fromDB(id, args);

		return c.getCount();
	}

	@Override
	public void clear(CounterKey id, Object... args) {

		getDao().delete(city.getId(), key(id, args));
	}

	@Override
	public boolean isMark(CounterKey id, Object... args) {

		return get(id, args) >= 1;
	}

	@Override
	public void mark(CounterKey id, Object... args) {

		T t = fromDB(id, args);

		t.setCount(1);

		getDao().save(t);
	}

	@Override
	public void set(CounterKey id, int value, Object... args) {

		T t = fromDB(id, args);

		t.setCount(value);

		getDao().save(t);
	}
}
