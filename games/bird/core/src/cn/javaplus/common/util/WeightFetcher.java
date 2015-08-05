package cn.javaplus.common.util;



public interface WeightFetcher<T> extends IntegerFetcher<T> {

	/**
	 * 获得元素t的权重列
	 * @param t
	 * @return
	 */
	@Override
	Integer get(T t);
}
