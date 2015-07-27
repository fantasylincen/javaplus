package org.javaplus.game.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bound {

	private Actor a;

	public Bound(Actor a) {
		this.a = a;
	}

	public void set(Actor actor) {
		actor.setX(a.getX());
		actor.setY(a.getY());
		actor.setWidth(a.getWidth());
		actor.setHeight(a.getHeight());
	}

}
