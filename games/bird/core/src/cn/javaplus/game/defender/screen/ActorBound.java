package cn.javaplus.game.defender.screen;

import com.badlogic.gdx.scenes.scene2d.IActor;

public class ActorBound implements Bound {

	private IActor	actor;

	public ActorBound(IActor actor) {
		this.actor = actor;
	}

	@Override
	public float getX() {
		return actor.getX();
	}

	@Override
	public float getY() {
		return actor.getY();
	}

	@Override
	public float getWidth() {
		return actor.getWidth();
	}

	@Override
	public float getHeight() {
		return actor.getHeight();
	}

	@Override
	public String toString() {
		return "ActorBound [getX()=" + getX() + ", getY()=" + getY() + ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + "]";
	}

	
}
