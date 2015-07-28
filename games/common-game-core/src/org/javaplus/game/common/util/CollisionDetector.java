package org.javaplus.game.common.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionDetector {

	/**
	 * x,y 是否在矩形中
	 * 
	 * @param rect
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean contains(RectFloat rect, float x, float y) {
		if (x < rect.getX()) {
			return false;
		}
		if (x > rect.getX() + rect.getW()) {
			return false;
		}
		if (y < rect.getY()) {
			return false;
		}
		if (y > rect.getY() + rect.getH()) {
			return false;
		}
		return true;
	}

	public boolean isCollideWith(Actor a1, Actor a2) {
		float x1 = a1.getX();
		float y1 = a1.getY();
		float w1 = a1.getWidth();
		float h1 = a1.getHeight();

		float x2 = a2.getX();
		float y2 = a2.getY();
		float w2 = a2.getWidth();
		float h2 = a2.getHeight();

		RectImpl r1 = new RectImpl(x1, y1, w1, h1);
		RectImpl r2 = new RectImpl(x2, y2, w2, h2);
		return isCollideWith(r1, r2);
	}

	private boolean isCollideWith(RectFloat r1, RectFloat r2) {
		if (isCornersIn(r1, r2)) {
			return true;
		}
		if (isCornersIn(r2, r1)) {
			return true;
		}

		return false;
	}

	/**
	 * 矩形2的 某个角 是否在矩形1 内部
	 * 
	 * @param r1
	 * @param r2
	 * @return
	 */
	private boolean isCornersIn(RectFloat r1, RectFloat r2) {
		if (contains(r1, r2.getX(), r2.getY())) {
			return true;
		}
		if (contains(r1, r2.getX() + r2.getW(), r2.getY())) {
			return true;
		}
		if (contains(r1, r2.getX() + r2.getW(), r2.getY() + r2.getH())) {
			return true;
		}
		if (contains(r1, r2.getX(), r2.getY() + r2.getH())) {
			return true;
		}
		return false;
	}

}
