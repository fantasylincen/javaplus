package cn.javaplus.crazy.input;

import com.badlogic.gdx.InputProcessor;

/**
 * 案件按下 发生异常
 */
public class KeyDownExceptionEvent {

	private int keycode;

	private Throwable exception;
	private InputProcessor processor;

	public KeyDownExceptionEvent(Throwable exception, InputProcessor processor,
			int keycode) {
		this.keycode = keycode;
		this.exception = exception;
		this.processor = processor;
	}

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
