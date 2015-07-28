package org.javaplus.game.common.b2;

import com.badlogic.gdx.math.Vector2;

public class b2Vec2 {

	public double y;
	public double x;

	public b2Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void Set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 getValue() {
		return new Vector2((float)x, (float) y);
	}

}
