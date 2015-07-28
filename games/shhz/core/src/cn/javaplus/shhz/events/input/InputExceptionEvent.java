package cn.javaplus.shhz.events.input;

import com.badlogic.gdx.InputProcessor;

/**
 * 输入时发生异常
 */
public class InputExceptionEvent {

	private Throwable e;
	private InputProcessor p;

	public InputExceptionEvent(Throwable e, InputProcessor p) {
		this.e = e;
		this.p = p;
	}

	public Throwable getException() {
		return e;
	}

	public InputProcessor getProcessor() {
		return p;
	}

}
