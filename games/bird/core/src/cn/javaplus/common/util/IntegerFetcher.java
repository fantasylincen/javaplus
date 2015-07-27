package cn.javaplus.common.util;



public interface IntegerFetcher<T> extends Fetcher<T, Integer> {

	/**
	 * 获得元素t的某个整型�?
	 * @param t
	 * @return
	 */
	@Override
	Integer get(T t);
}
