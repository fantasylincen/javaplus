package cn.mxz.util.counter;

public interface CounterDao<T> {

	T createDTO();

	T get(String key, String id);

	void delete(String key, String id);

	void save(T t);
}
