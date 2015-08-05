package cn.javaplus.shhz.util;

import com.badlogic.gdx.math.Vector2;

public class VectorInt {

	int x;
	int y;

	public VectorInt() {
	}

	public VectorInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public VectorInt(VectorInt v) {
		this(v.x, v.y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static final VectorInt parse(Vector2 v) {
		return new VectorInt((int) v.x, (int) v.y);
	}

	public static final Vector2 parse(VectorInt v) {
		return new Vector2(v.x, v.y);
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void set(VectorInt p) {
		set(p.getX(), p.getY());
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
}
