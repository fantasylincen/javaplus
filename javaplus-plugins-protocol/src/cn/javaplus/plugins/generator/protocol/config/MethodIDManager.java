package cn.javaplus.plugins.generator.protocol.config;

import java.util.concurrent.atomic.AtomicLong;

public class MethodIDManager {
	private static AtomicLong	classId		= new AtomicLong();
	private static AtomicLong	methodId	= new AtomicLong();

	/**
	 * 往下挑一个类
	 */
	public static void nextClass() {
		methodId.set(0);
		classId.addAndGet(1);
	}

	/**
	 * 往下跳一个方法
	 */
	public static void nextMethod() {
		methodId.addAndGet(1);
	}

	/**
	 * 获得方法ID
	 * 
	 * @return
	 */
	public static long getMethodId() {
		return classId.longValue() * 10000 + methodId.longValue();
	}

	public static void reset() {
		classId = new AtomicLong();
		methodId = new AtomicLong();
	}
}
