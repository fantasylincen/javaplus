package cn.javaplus.shhz.events.input;

import com.badlogic.gdx.InputProcessor;

/**
 * 拖拽时发生异常
 */
public class ToutchDragExceptionEvent {

	private int x;
	private int y;
	private int pointer;

	private Throwable exception;
	private InputProcessor processor;

	public ToutchDragExceptionEvent(Throwable e, InputProcessor p, int x,
			int y, int pointer) {
		this.x = x;
		this.y = y;
		this.pointer = pointer;
		this.exception = e;
		this.processor = p;
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

}
