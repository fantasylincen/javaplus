package cn.javaplus.crazy.components.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public final class FastTabListener extends ActorGestureListener {

	private long lastMilles;
	private int count;

	@Override
	public final void tap(InputEvent event, float x, float y, int cnt,
			int button) {

		if (count >= 3) {
			fastTab(event, x, y, count, button);
			count = 0;
		}
		long now = System.currentTimeMillis();
		if (now - lastMilles < 100) {
			count++;
		} else {
			count = 0;
		}
		lastMilles = now;
	}

	protected void fastTab(InputEvent event, float x, float y, int count,
			int button) {
	}

	@Override
	public final void fling(InputEvent event, float velocityX, float velocityY,
			int button) {
	}

	@Override
	public final GestureDetector getGestureDetector() {
		return super.getGestureDetector();
	}

	@Override
	public final boolean handle(Event e) {
		return super.handle(e);
	}

	@Override
	public final void touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
	}

	@Override
	public final void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
	}

	@Override
	public final boolean longPress(Actor actor, float x, float y) {
		return super.longPress(actor, x, y);
	}

	@Override
	public final void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
	}

	@Override
	public final void zoom(InputEvent event, float initialDistance,
			float distance) {
	}

	@Override
	public final void pinch(InputEvent event, Vector2 initialPointer1,
			Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
	}

	@Override
	public final Actor getTouchDownTarget() {
		return super.getTouchDownTarget();
	}

}
