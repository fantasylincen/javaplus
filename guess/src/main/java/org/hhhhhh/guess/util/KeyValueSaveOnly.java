package org.hhhhhh.guess.util;

public interface KeyValueSaveOnly {
	
	/**
	 * 设值
	 * @param key
	 * @param value
	 */
	void set(Object key, Object value);
	
	/**
	 * 对某个已存在 的整型变量加
	 * 如果不是整型变量, 则报错
	 * 如果之前变量不存在, 默认初始为0
	 */
	void add(Object key, long add);
	
	

}