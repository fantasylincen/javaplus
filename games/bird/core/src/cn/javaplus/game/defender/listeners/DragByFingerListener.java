package cn.javaplus.game.defender.listeners;

import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class DragByFingerListener extends ActorGestureAdaptor {

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		IActor target = event.getTarget();
		target.translate(deltaX, deltaY);
	}
}
