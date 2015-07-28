package cn.javaplus.shhz.random;

public interface Fetcher<T, R> {

	R get(T t);

}
