package cn.javaplus.events;

/**
 * 事件触发器
 * @author 林岑
 *
 * @param <L>
 * @param <E>
 */
public interface EventDispatcher2 {

	/**
	 * 添加一个监听器
	 * @param id		事件ID
	 * @param listener	监听器
	 */
	void addListener(Listener2 listener);

	/**
	 * 清空所有监听器
	 */
	void clear();

	/**
	 * 触发某个事件
	 * @param e		事件
	 */
	void dispatchEvent(Object e);

	/**
	 * 移除监听器
	 * @param listener
	 */
	void removeListener(Listener2 listener);
}