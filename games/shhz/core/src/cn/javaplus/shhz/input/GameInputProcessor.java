package cn.javaplus.shhz.input;

import java.util.List;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.shhz.collections.Lists;
import cn.javaplus.shhz.events.input.InputExceptionEvent;
import cn.javaplus.shhz.events.input.KeyDownExceptionEvent;
import cn.javaplus.shhz.events.input.KeyTypedExceptionEvent;
import cn.javaplus.shhz.events.input.KeyUpExceptionEvent;
import cn.javaplus.shhz.events.input.MouseMovedExceptionEvent;
import cn.javaplus.shhz.events.input.ScrolledExceptionEvent;
import cn.javaplus.shhz.events.input.ToutchDownExceptionEvent;
import cn.javaplus.shhz.events.input.ToutchDragExceptionEvent;
import cn.javaplus.shhz.events.input.ToutchUpExceptionEvent;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	List<InputProcessor> processor;

	public GameInputProcessor() {
		processor = Lists.newArrayList();
	}

	public boolean add(InputProcessor object) {
		return processor.add(object);
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		for (InputProcessor p : processor) {
			try {
				// Log.d("GameInputProcessor.touchDown ", p);
				if (p.touchDown(x, y, pointer, button)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new ToutchDownExceptionEvent(e, p, x, y,
						pointer, button));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		for (InputProcessor p : processor) {
			try {
				if (p.touchUp(x, y, pointer, button)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new ToutchUpExceptionEvent(e, p, x, y, pointer,
						button));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		for (InputProcessor p : processor) {
			try {
				if (p.touchDragged(x, y, pointer)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new ToutchDragExceptionEvent(e, p, x, y,
						pointer));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean keyDown(int keycode) {
		for (InputProcessor p : processor) {
			try {
				if (p.keyDown(keycode)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new KeyDownExceptionEvent(e, p, keycode));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean keyUp(int keycode) {
		for (InputProcessor p : processor) {
			try {
				if (p.keyUp(keycode)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new KeyUpExceptionEvent(e, p, keycode));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean keyTyped(char character) {
		for (InputProcessor p : processor) {
			try {
				if (p.keyTyped(character)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new KeyTypedExceptionEvent(e, p, character));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean mouseMoved(int x, int y) {
		for (InputProcessor p : processor) {
			try {
				if (p.mouseMoved(x, y)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new MouseMovedExceptionEvent(e, p, x, y));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}

	public boolean scrolled(int amount) {
		for (InputProcessor p : processor) {
			try {
				if (p.scrolled(amount)) {
					return true;
				}
			} catch (Throwable e) {
				Events.dispatch(new ScrolledExceptionEvent(e, p, amount));
				Events.dispatch(new InputExceptionEvent(e, p));
				return false;
			}
		}
		return false;
	}
}