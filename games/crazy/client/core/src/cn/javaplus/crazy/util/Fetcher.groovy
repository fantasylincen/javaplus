package cn.javaplus.crazy.util;

public interface Fetcher<T, R> {

	R get(T t);

}
