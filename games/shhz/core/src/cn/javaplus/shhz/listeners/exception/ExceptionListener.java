package cn.javaplus.shhz.listeners.exception;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.input.InputExceptionEvent;
import cn.javaplus.shhz.log.Log;

public class ExceptionListener implements Listener<InputExceptionEvent> {

	@Override
	public void onEvent(InputExceptionEvent e) {
		Throwable exception = e.getException();
		Log.e(exception);
		if (D.IS_SHOW_ERROR_MESSAGE) {
			exception.printStackTrace();
		}
	}

}
