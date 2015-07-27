package cn.javaplus.game.defender.listeners;

import cn.javaplus.game.defender.camera.GameCameraImpl.MoveEvent;
import cn.javaplus.game.defender.mover.Moveable;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class FollowListener implements EventListener {

	private Moveable mover;

	public FollowListener(Moveable mover) {
		this.mover = mover;
	}

	@Override
	public boolean handle(Event event) {
		if (event instanceof MoveEvent) {
			MoveEvent e = (MoveEvent) event;
			Vector2 d = e.getDis();
			mover.translate(d.getX(), d.getY());
			return true;
		}
		return false;
	}
}
