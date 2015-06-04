package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 用户请求发生异常时
 *
 * @author 林岑
 *
 */
public class RequestExceptionEvent {

	private City		user;
	private Throwable	exception;

	public RequestExceptionEvent(City user, Throwable exception) {
		this.user = user;
		this.exception = exception;
	}

	public City getUser() {
		return user;
	}

	public Throwable getException() {
		return exception;
	}
}
