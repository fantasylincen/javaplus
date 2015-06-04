package cn.javaplus.events;


/**
 * 
 * 监听器
 * 
 * {@link Events#loadListeners(String)} 可以加载某个包下所有的 Listener的子类, 并将其添加到侦听列表中, 
 * 然后使用{@link Events#dispatch(Object)} 派发对应事件
 * @author 林岑
 *
 * @param <T> 事件类型
 * 
 */
public interface Listener<T> {

	/**
	 * 当事件出发时, 调用该方法
	 * @param e
	 */
	void onEvent(T e);

}
