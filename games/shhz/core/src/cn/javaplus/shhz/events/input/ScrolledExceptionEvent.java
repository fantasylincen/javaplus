package cn.javaplus.shhz.events.input;

import com.badlogic.gdx.InputProcessor;

public class ScrolledExceptionEvent {

	private int amount;

	private Throwable exception;
	private InputProcessor processor;

	public ScrolledExceptionEvent(Throwable exception,
			InputProcessor processor, int amount) {
		this.amount = amount;
		this.exception = exception;
		this.processor = processor;
	}

	public Throwable getException() {
		return exception;
	}

	public InputProcessor getProcessor() {
		return processor;
	}

	public int getAmount() {
		return amount;
	}

}
