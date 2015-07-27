package cn.javaplus.shhz.util;

import cn.javaplus.game.shhz.define.D;

import com.badlogic.gdx.math.Vector2;

public class GridCoordinate {

	// line : y = k(x - b)
	// y = kx - kb
	// kb = kx - y
	// b = x - y / k
	float b1;
	float b2;
	float k1;
	float k2;
	float x0;
	float y0;

	public GridCoordinate() {
		k1 = -(D.MAP_GRID_H + 0f) / D.MAP_GRID_W;
		k2 = (D.MAP_GRID_H + 0f) / D.MAP_GRID_W;
		x0 = D.MAP_GRID_W * D.MAP_GRID_X_START + D.MAP_GRID_W_HALF;
		y0 = D.MAP_GRID_H * D.MAP_GRID_Y_START;
		b1 = x0 - y0 / k1;
		b2 = x0 - y0 / k2;
	}

	// X轴: 下方 到 右方 增大
	// Y轴: 下方 到 左方 增大
	public Vector2 translate(float x, float y) {

		float k4 = k2;
		float b4 = (k4 * x - y) / k4;

		float k3 = k1;
		float b3 = (k3 * x - y) / k3;

		float x32 = (k2 * b2 - k3 * b3) / (k2 - k3);
		float y32 = k3 * (x32 - b3);

		float x41 = (k1 * b1 - k4 * b4) / (k1 - k4);
		float y41 = k4 * (x41 - b4);

		float d41 = distance(x41, y41);
		float d32 = distance(x32, y32);

		if (x41 > x0) {
			d41 = -d41;
		}
		if (x32 < x0) {
			d32 = -d32;
		}

		return new Vector2(d32, d41);
	}

	private float distance(float x, float y) {
		float xd = x - x0;
		float yd = y - y0;
		double d = Math.sqrt(xd * xd + yd * yd);
		return (float) d;
	}
}
