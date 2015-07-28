package org.javaplus.game.common.actor;

import java.util.Map;

import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.google.common.collect.Maps;

public class GroupMap extends Group {

	private Map<String, Actor> actors = Maps.newHashMap();

	protected void putActor(Actor actor, Object... keys) {
		if (actor == null)
			throw new NullPointerException();
		removeActor(keys);
		actors.put(key(keys), actor);
		addActor(actor);
	}

	public Actor removeActor(Object... keys) {
		removeActor(getActor(keys));
		return actors.remove(key(keys));
	}

	public Actor getActor(Object... keys) {
		return actors.get(key(keys));
	}

	private String key(Object... keys) {
		return Util.Collection.linkWith(":", keys);
	}

}
