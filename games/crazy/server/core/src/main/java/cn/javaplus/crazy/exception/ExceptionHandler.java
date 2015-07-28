package cn.javaplus.crazy.exception;

import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.handler.ExceptionCaughtEvent;
import cn.javaplus.log.Log;

public class ExceptionHandler implements Listener<ExceptionCaughtEvent> {

	@Override
	public void onEvent(ExceptionCaughtEvent e) {
		Throwable error = e.getEvent().getCause();
		String message = error.getMessage();
		if ("远程主机强迫关闭了一个现有的连接。".equals(message)) {
			Log.e(message, e.getContext().getChannel().getId());
		} else {
			Log.e(error);
		}
	}

}
