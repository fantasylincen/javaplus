package cn.javaplus.crazy.exception;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.input.InputExceptionEvent;
import cn.javaplus.crazy.login.MessageBox;

public class ExceptionListener implements Listener<InputExceptionEvent> {

	@Override
	public void onEvent(InputExceptionEvent e) {
		MessageBox messageBox = AppContext.getMessageBox();
		Throwable exception = e.getException();
		String message = exception.getMessage();
		messageBox.showMessage(message);
	}

}
