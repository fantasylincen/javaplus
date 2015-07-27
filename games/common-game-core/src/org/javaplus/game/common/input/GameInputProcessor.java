package org.javaplus.game.common.input;

import java.util.List;

import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	public interface InputProcessorListener {

		void onException(Throwable e);

	}

	private InputProcessor p;
	private List<InputProcessorListener> listeners;

	public GameInputProcessor() {
		listeners = Lists.newArrayList();
	}

	public void set(InputProcessor processor) {
		this.p = processor;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		try {
			return p.touchDown(x, y, pointer, button);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		try {
			boolean touchUp = p.touchUp(x, y, pointer, button);
			return touchUp;
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean touchDragged(int x, int y, int pointer) {
		try {
			return p.touchDragged(x, y, pointer);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean keyDown(int keycode) {
		try {
			return p.keyDown(keycode);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean keyUp(int keycode) {
		try {
			return p.keyUp(keycode);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean keyTyped(char character) {
		try {
			return p.keyTyped(character);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean mouseMoved(int x, int y) {
		try {
			return p.mouseMoved(x, y);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	public boolean scrolled(int amount) {
		try {
			return p.scrolled(amount);
		} catch (Throwable e) {
			onException(e);
			return false;
		}
	}

	private void onException(Throwable e) {
		for (InputProcessorListener l : listeners) {
			l.onException(e);
		}
	}
}