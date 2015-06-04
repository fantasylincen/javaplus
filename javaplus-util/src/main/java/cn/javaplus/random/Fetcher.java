package cn.javaplus.random;

public interface Fetcher<T, R> {

	R get(T t);

}
