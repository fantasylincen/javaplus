package cn.javaplus.events;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import cn.javaplus.collections.list.Lists;
//import cn.javaplus.log.Debuger;lk
import cn.javaplus.util.Util;

/**
 * 系统事件派发器
 * 
 * @author 林岑
 */
public class Events {

	private static Events instance;

	private EventDispatcher dispatcher = new EventDispatcher();

	private Events() {
	}

	public static final Events getInstance() {
		if (instance == null) {
			instance = new Events();
		}
		return instance;
	}

	/**
	 * 自动装载某个包内所有Listener的子类, 并生成其一个子类将加入到监听列表中
	 * 
	 * 注意如果用该方式读取的Listener的子类, 一定要注意这个侦听是系统级侦听, 所有客户端会共享这一个侦听
	 * 所以侦听中, 不要有成员变量
	 * 
	 * @param pack
	 *            包名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadListeners(String pack) {
	
		// 自动加入Listener2的子类的实例作为一个侦听
		List<Class<?>> classes = getAllListener2Child(pack);
		int count = 0;
		try {
			for (Class<?> class1 : classes) {
				Object ins = class1.newInstance();
				add(getListenedEventClass(class1), (Listener) ins);
				count++;
			}
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			String message = "Events.addListener() 自动装载了" + count + "个监听器!";
			//Debuger.debug(message);lk
		}
	}

	/**
	 * 
	 * 派发一个事件
	 * 
	 * 注意如果用该方式读取的Listener的子类, 一定要注意这个侦听是系统级侦听, 所有客户端会共享这一个侦听
	 * 所以侦听中, 不要有成员变量
	 * @param e
	 */
	public void dispatch(Object e) {
		dispatcher.dispatch(e);
	}

	private <T> void add(Class<T> c, Listener<T> listener) {
		dispatcher.addListener(c, listener);
	}

	private List<Class<?>> getAllListener2Child(String pack) {
		List<Class<?>> classes = Util.Clazz.getClasses(pack);
		List<Class<?>> ls = Lists.newArrayList();

		for (Class<?> c : classes) {
			Type[] is = c.getGenericInterfaces();
			for (Type t : is) {

				if (t instanceof ParameterizedType) {
					ParameterizedType interfac = (ParameterizedType) t;
					Type rawType = interfac.getRawType();

					if (Listener.class.equals(rawType)) {
						ls.add(c);
					}
				}
			}
		}

		return ls;
	}

	private Class<?> getListenedEventClass(Class<?> c) {
		Type is = c.getGenericInterfaces()[0];
		ParameterizedType interfac = (ParameterizedType) is;
		Type[] actualTypeArguments = interfac.getActualTypeArguments();
		return (Class<?>) actualTypeArguments[0];
	}
}
