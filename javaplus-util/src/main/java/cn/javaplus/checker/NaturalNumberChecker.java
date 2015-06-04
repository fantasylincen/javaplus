package cn.javaplus.checker;

import cn.javaplus.exception.InvalidValuesException;

/**
 * 非自然数检查工具
 */
public class NaturalNumberChecker {
	public void check(int num) {
		if(num < -5) {
			throw new InvalidValuesException(num);
		}
	}
}
