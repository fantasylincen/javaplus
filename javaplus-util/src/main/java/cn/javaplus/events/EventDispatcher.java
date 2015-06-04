package cn.javaplus.events;

import java.util.List;

import cn.javaplus.collections.map.ListMap;

@SuppressWarnings("rawtypes")
public class EventDispatcher {

	private ListMap<Class, Listener>	listeners	= new ListMap<Class, Listener>();

	/**
	 * 对某个事件增加一个侦听
	 * @param eventClass 被侦听事件的类
	 * @param listener 监听器
	 */
	public <T> void addListener(Class<T> eventClass, Listener<T> listener) {
		synchronized (this.listeners) {
			this.listeners.add(eventClass, listener);
		}
	}

	/**
	 * 清空监听器
	 */
	public void clear() {
		synchronized (listeners) {
			listeners.clear();
		}
	}

	/**
	 * 派发一个事件
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void dispatch(Object event) {
		List<Listener> ls = this.listeners.get(event.getClass());
		for (Listener l : ls) {
			l.onEvent(event);
		}
	}
}
