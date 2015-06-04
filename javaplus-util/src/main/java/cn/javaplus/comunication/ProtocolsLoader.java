package cn.javaplus.comunication;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.util.Util;

public class ProtocolsLoader {

	private static List<Class<?>>			classz;

	/**
	 * 所有通信接口类
	 */
	private static Map<String, Class<?>>	communicationInterfaces;

	/**
	 * <类名:方法名, 方法>
	 */
	private static Map<String, Method>		communicationMethods;

	/**
	 * <类名:方法名, 方法实现>
	 */
	private static Map<String, Method>		communicationMethodsImpl;

	/**
	 * <接口名, 实现>
	 */
	private static Map<String, Class<?>>	communicationClassImpl;

	/**
	 * 注入外部Bean工厂
	 *
	 * @param externalBeanFactory
	 */
	public static void setExternalBeanFactory(BeanFactory externalBeanFactory) {
		ProtocolsLoader.externalBeanFactory = externalBeanFactory;
	}

	private static BeanFactory	externalBeanFactory;

	static {
		classz = Util.Clazz.getClasses("cn.mxz");
//		for (Class<?> c : classz) {
//			if(c.getName().contains("ChuangZhenTransform")) {
//				System.out.println("ProtocolsLoader.enclosing_method()" + c);
//			}
//		}
		communicationInterfaces = getAllCommunicationInterfaces();
		loadAllImpl();
		check();// 检测所有重复实现, 每个Communication接口有且仅有一个实现
		loadCommunicationMethods();
	}

	private static void loadAllImpl() {
		communicationMethodsImpl = Maps.newHashMap();
		communicationClassImpl = Maps.newHashMap();
		for (Class<?> c : communicationInterfaces.values()) {
			List<Class<?>> impls = getAllImpls(c);
			checkOnlyOne(c, impls);

			Class<?> impl = impls.get(0);
			putMethodImpl(c, impl);
			putMethodImpl(c, impl);
			communicationClassImpl.put(c.getName(), impl);
//			System.out.println("ProtocolsLoader.loadAllImpl()" + c.getName() + ":" + impl);
		}
	}

	private static void putMethodImpl(Class<?> interfac, Class<?> impl) {
		Method[] methods = interfac.getMethods();
		for (Method method : methods) {
			String key = getKey(interfac, method);
			Method implMethod = getImpl(method, impl);
			communicationMethodsImpl.put(key, implMethod);
		}
	}

	private static Method getImpl(Method method, Class<?> impl) {
		try {
			return impl.getMethod(method.getName(), method.getParameterTypes());
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	/**
	 * 读取所有通信接口的方法
	 */
	private static void loadCommunicationMethods() {
		communicationMethods = Maps.newHashMap();
		for (Class<?> c : communicationInterfaces.values()) {
			Method[] all = c.getMethods();
			for (Method method : all) {
				communicationMethods.put(getKey(c, method), method);
			}
		}
	}

	private static String getKey(Class<?> c, Method method) {
		return c.getName() + ":" + method.getName();
	}

	private static void check() {
		for (Class<?> c : communicationInterfaces.values()) {
			List<Class<?>> impls = getAllImpls(c);
			checkOnlyOne(c, impls);
			checkUserSetter(impls);
		}
		checkMethodRepeated();
	}

	private static void checkOnlyOne(Class<?> c, List<Class<?>> impls) {
		if (impls.size() != 1) {
			throw new RuntimeException(c + "应该有且仅由一个实现: " + impls);
		}
	}

	private static void checkUserSetter(List<Class<?>> impls) {
		for (Class<?> c : impls) {
			if (!hasSetUser(c)) {
				throwException(c);
			}
		}
	}

	private static boolean hasSetUser(Class<?> c) {
		Method[] ms = c.getMethods();
		for (Method method : ms) {
			if (isSetUser(method)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isSetUser(Method method) {
		String name = method.getName();
		if (!name.equals("setUser")) {
			return false;
		}

		Class<?>[] pts = method.getParameterTypes();
		if (pts.length != 1) {
			return false;
		}

		if (pts[0].equals(ProtocolUser.class)) {
			return true;
		}

		return Util.Clazz.isChild(pts[0], ProtocolUser.class);
	}

	private static void throwException(Class<?> c) {
		throw new RuntimeException("需要一个方法: public void setUser(ProtocolUser user)  或者 (? extends ProtocolUser user)  " + c);
	}

	private static void checkMethodRepeated() {
		for (Class<?> c : communicationInterfaces.values()) {
			Set<String> set = Sets.newHashSet();
			Method[] methods = c.getMethods();
			for (Method method : methods) {
				set.add(method.getName());
			}
			if (set.size() != methods.length) {
				throw new RuntimeException("不可有重名方法:" + set);
			}
		}

	}

	private static List<Class<?>> getAllImpls(Class<?> c) {
		List<Class<?>> ls = Lists.newArrayList();
		for (Class<?> child : classz) {
			Class<?>[] interfaces = child.getInterfaces();
			for (Class<?> class1 : interfaces) {
				if (class1.equals(c)) {
					ls.add(child);
					break;
				}
			}
		}
		return ls;
	}

	private static Map<String, Class<?>> getAllCommunicationInterfaces() {
		Map<String, Class<?>> map = Maps.newHashMap();
		for (Class<?> c : classz) {
			if (c.getAnnotation(Communication.class) != null) {
				map.put(c.getName(), c);
			}
		}
		return map;
	}

	public static Class<?> getClassImpl(String className) {
		return communicationClassImpl.get(className);
	}

	public static Method getMethodImpl(Request request) {
		return communicationMethodsImpl.get(request.getClassName() + ":" + request.getMethodName());
	}

	/**
	 * 获取一个绑定了用户ID的通信对象
	 *
	 * @param request
	 *            类名
	 * @param id
	 *            玩家ID
	 */
	public static <U extends ProtocolUser> Object getBindUserIdObject(Request request, U user) {

		Class<?> c = ProtocolsLoader.getClassImpl(request.getClassName());

		if (c == null) {
			throw new NullPointerException(request.getClassName());
		}

		Object ins;
		try {

			ins = getBean(c, request.getClassName());

			bindUser(user, ins);

		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
		return ins;
	}

	public static <U extends ProtocolUser> void bindUser(U user, Object ins) {
		try {

			Class<? extends Object> cs = ins.getClass();
			Method[] methods = cs.getMethods();

			for (Method method : methods) {
				String name = method.getName();
				if (name.equals("setUser")) {
					method.invoke(ins, user);
					return;
				}
			}
			throwException(cs);
			//
			// Method setUser = cs.getMethod("setUser", user.getClass());
			// setUser.invoke(ins, user);
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	private static <U extends ProtocolUser> Object getBean(Class<?> c, String className) throws InstantiationException, IllegalAccessException {

		if (externalBeanFactory == null) {
			return c.newInstance();
		}

		c = ProtocolsLoader.getClass(className);
		return externalBeanFactory.getBean(c);
	}

	/**
	 * 制定接口类
	 *
	 * @param className
	 * @return
	 */
	public static Class<?> getClass(String className) {
		Class<?> class1 = communicationInterfaces.get(className);
		if (class1 == null) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw Util.Exception.toRuntimeException(e);
			}
		}
		return class1;
	}

	/**
	 * 取得指定的方法
	 *
	 * @param rq
	 *            包含类名和方法名的请求
	 * @return
	 */
	public static Method getMethod(Request rq) {
		return communicationMethods.get(rq.getClassName() + ":" + rq.getMethodName());
	}

	public static Method getMethodImpl(Request rq, Object ins) {
		Method method = getMethodImpl(rq);
		Method[] ms = ins.getClass().getMethods();
		for (Method m : ms) {
			if(equals(m, method)) {
				return m;
			}
		}
		throw new RuntimeException("无法找到相同方法");
	}

	private static boolean equals(Method m, Method method) {
		String n1 = m.getName();
		String n2 = method.getName();
		if(!n1.equals(n2)) {
			return false;
		}

		Class<?>[] c1 = m.getParameterTypes();
		Class<?>[] c2 = method.getParameterTypes();

		if(c1.length != c2.length) {
			return false;
		}

		for (int i = 0; i < c2.length; i++) {
			Class<?> c11 = c1[i];
			Class<?> c22 = c2[i];
			if(!c11.equals(c22)) {
				return false;
			}
		}
		return true;
	}

}
