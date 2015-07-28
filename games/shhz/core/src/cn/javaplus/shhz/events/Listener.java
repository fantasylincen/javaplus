package cn.javaplus.shhz.events;

public interface Listener<T> {

	/**
	 * 当事件出发时, 调用该方法
	 * 
	 * @param e
	 */
	void onEvent(T e);

}
