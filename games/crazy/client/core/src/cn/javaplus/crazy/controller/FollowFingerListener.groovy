package cn.javaplus.crazy.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * 手指跟随
 */
public class FollowFingerListener extends ActorGestureListener {

	private float sx;
	private float sy;

	public void drag(InputEvent event, float deltaX, float deltaY) {

	}

	@Override
	public final void touchDown(InputEvent event, float x, float y,
			int pointer, int button) {
		this.sx = x;
		this.sy = y;
	}

	@Override
	public final void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		drag(event, x - sx, y - sy);
	}

	@Override
	public final void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		sx = 0;
		sy = 0;
	}

}
