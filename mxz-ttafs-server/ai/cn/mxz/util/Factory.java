package cn.mxz.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Factory {

	private static ApplicationContext	CONTEXT;

	private static ApplicationContext getContext() {

		if (CONTEXT == null) {

//			CONTEXT = new ClassPathXmlApplicationContext("beans.xml");

			CONTEXT = new FileSystemXmlApplicationContext("res/beans.xml");
		}
		return CONTEXT;
	}

	public static Object get(String name) {
		return getContext().getBean(name);
	}

	public static Object get(Class<?> c) {

		ApplicationContext ct = getContext();

		return ct.getBean(c);
	}
//
//	/**
//	 * 获得类型为type的对象
//	 *
//	 * @param name
//	 *            对象名字
//	 * @param type
//	 *            对象类型
//	 * @param args
//	 *            参数列表
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> T get(Class<T> type, String name, Object... args) {
//		return (T) getContext().getBean(name, args);
//	}

}
