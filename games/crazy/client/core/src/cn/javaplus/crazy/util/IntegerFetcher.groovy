package cn.javaplus.crazy.util;

public interface IntegerFetcher<T> extends Fetcher<T, Integer> {

	/**
	 * 获得元素t的某个整型值
	 * 
	 * @param t
	 * @return
	 */
	@Override
	Integer get(T t);
}
