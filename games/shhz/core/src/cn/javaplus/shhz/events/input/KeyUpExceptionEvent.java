package cn.javaplus.shhz.events.input;

import com.badlogic.gdx.InputProcessor;

public class KeyUpExceptionEvent {

	private int keycode;

	public KeyUpExceptionEvent(Throwable exception, InputProcessor processor,
			int keycode) {
		this.keycode = keycode;
		this.exception = exception;
		this.processor = processor;
	}

	private Throwable exception;
	private InputProcessor processor;

	public Throwable getException() {
		return exception;
	}

	public InputProcessor getProcessor() {
		return processor;
	}

	public int getKeycode() {
		return keycode;
	}

}
