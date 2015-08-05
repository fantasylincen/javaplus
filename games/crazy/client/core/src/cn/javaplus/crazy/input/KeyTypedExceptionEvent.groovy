package cn.javaplus.crazy.input;

import com.badlogic.gdx.InputProcessor;

public class KeyTypedExceptionEvent {

	private char character;

	public KeyTypedExceptionEvent(Throwable exception,
			InputProcessor processor, char character) {
		this.character = character;
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

	public char getCharacter() {
		return character;
	}

}
