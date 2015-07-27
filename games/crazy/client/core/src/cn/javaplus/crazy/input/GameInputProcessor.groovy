package cn.javaplus.crazy.input;

import cn.javaplus.crazy.events.Events;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	private InputProcessor p;

	public GameInputProcessor() {
	}

	public void set(InputProcessor processor) {
		this.p = processor;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		try {
			return p.touchDown(x, y, pointer, button);
		} catch (Throwable e) {
			Events.dispatch(new ToutchDownExceptionEvent(e, p, x, y, pointer,
					button));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		try {
			boolean touchUp = p.touchUp(x, y, pointer, button);
			Events.dispatch(new TouchUpEvent());
			return touchUp;
		} catch (Throwable e) {
			Events.dispatch(new ToutchUpExceptionEvent(e, p, x, y, pointer,
					button));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean touchDragged(int x, int y, int pointer) {
		try {
			return p.touchDragged(x, y, pointer);
		} catch (Throwable e) {
			Events.dispatch(new ToutchDragExceptionEvent(e, p, x, y, pointer));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean keyDown(int keycode) {
		try {
			return p.keyDown(keycode);
		} catch (Throwable e) {
			Events.dispatch(new KeyDownExceptionEvent(e, p, keycode));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean keyUp(int keycode) {
		try {
			return p.keyUp(keycode);
		} catch (Throwable e) {
			Events.dispatch(new KeyUpExceptionEvent(e, p, keycode));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean keyTyped(char character) {
		try {
			return p.keyTyped(character);
		} catch (Throwable e) {
			Events.dispatch(new KeyTypedExceptionEvent(e, p, character));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean mouseMoved(int x, int y) {
		try {
			return p.mouseMoved(x, y);
		} catch (Throwable e) {
			Events.dispatch(new MouseMovedExceptionEvent(e, p, x, y));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}

	public boolean scrolled(int amount) {
		try {
			return p.scrolled(amount);
		} catch (Throwable e) {
			Events.dispatch(new ScrolledExceptionEvent(e, p, amount));
			Events.dispatch(new InputExceptionEvent(e, p));
			return false;
		}
	}
}