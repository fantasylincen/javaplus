package cn.mxz.listeners;

import cn.mxz.base.exception.ExceptionHandler;
import cn.mxz.base.exception.ExceptionHandlerImpl;
import cn.mxz.events.Listener;
import cn.mxz.events.RequestExceptionEvent;

// 用户请求异常时
public class ExceptionProcess implements Listener<RequestExceptionEvent> {

	@Override
	public void onEvent(RequestExceptionEvent event) {

		final ExceptionHandler handler = ExceptionHandlerImpl.getInstance();

		handler.filter(event.getException()); // 过滤异常信息

		handler.process(event.getException(), event.getUser());
	}


}
