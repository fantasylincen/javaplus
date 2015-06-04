package cn.javaplus.communication;

import cn.javaplus.comunication.ProtocolUser;

/**
 * 一个操作
 * @author 林岑
 */
public interface Action<U extends ProtocolUser> {

	/**
	 * 执行前
	 */
	void excuteBefore(U user, Object... args);

	/**
	 * 执行
	 */
	void excute(U user, Object... args);

	/**
	 * 执行后
	 */
	void excuteAfter(U user, Object... args);

	/**
	 * 执行发生异常后
	 */
	void excuteException(U user, Object... args);

	/**
	 * 执行完毕(不管异常不异常)
	 */
	void excuteFinally(U user, Object... args);

}
