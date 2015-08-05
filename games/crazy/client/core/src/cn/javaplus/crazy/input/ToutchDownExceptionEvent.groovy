package cn.javaplus.crazy.input;

import com.badlogic.gdx.InputProcessor;

public class ToutchDownExceptionEvent {

	private int x;
	private int y;
	private int pointer;
	private int button;

	private Throwable exception;
	private InputProcessor processor;

	public ToutchDownExceptionEvent(Throwable exception,
			InputProcessor processor, int x, int y, int pointer, int button) {
		this.x = x;
		this.y = y;
		this.pointer = pointer;
		this.button = button;
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

	public int getPointer() {
		return pointer;
	}

	public int getButton() {
		return button;
	}

}
