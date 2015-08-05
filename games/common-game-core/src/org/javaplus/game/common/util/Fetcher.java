package org.javaplus.game.common.util;

public interface Fetcher<T, R> {

	R get(T t);

}
