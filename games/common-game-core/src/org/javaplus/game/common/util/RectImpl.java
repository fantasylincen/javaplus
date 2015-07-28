package org.javaplus.game.common.util;

public class RectImpl implements RectFloat {

	private float x;
	private float y;
	private float w;
	private float h;

	public RectImpl(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

}
