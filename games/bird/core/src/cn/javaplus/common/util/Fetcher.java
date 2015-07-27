package cn.javaplus.common.util;

public interface Fetcher<T, R> {

	R get(T t);

}
