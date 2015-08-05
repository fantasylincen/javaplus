package cn.javaplus.shhz.events.input;

import com.badlogic.gdx.InputProcessor;

public class MouseMovedExceptionEvent {

	private int x;
	private int y;

	private Throwable exception;
	private InputProcessor processor;

	public MouseMovedExceptionEvent(Throwable exception,
			InputProcessor processor, int x, int y) {
		this.x = x;
		this.y = y;
		this.exception = exception;
		this.processor = processor;
	}

	public Throwable getException() {
		return exception;
	}

	public InputProcessor getProcessor() {
		return processor;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
