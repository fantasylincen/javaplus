package cn.javaplus.events;


/**
 * 事件监听器
 * @author 林岑
 *
 */
public interface Listener2 {

	/**
	 * 当事件发生时调用
	 * @param e
	 */
	void onEvent(Object e);

	/**
	 * 被侦听的事件类型
	 * @return
	 */
	Class<?> getEventListendClass();
}
