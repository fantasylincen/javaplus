package cn.javaplus.shhz.components;

import cn.javaplus.shhz.util.RhombusFloat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapGrid extends Actor implements RhombusFloat {

	int xIndex;
	int yIndex;
	float w;
	float h;
	private Texture texture;
	private boolean occupied;
	private float x;
	private float y;

	public MapGrid(Texture texture, int xIndex, int yIndex, float x, float y, int w, int h) {
		this.texture = texture;
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Deprecated
	public void setX(float x) {
	}

	@Deprecated
	public void setY(float y) {
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color old = batch.getColor();
		batch.setColor(getColor());
		batch.draw(texture, getX(), getY());
		batch.setColor(old);
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public int getXIndex() {
		return xIndex;
	}
	
	public int getYIndex() {
		return yIndex;
	}
	
	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	public void unOccupied() {
		occupied = false;
		setColor(Color.WHITE);
	}

	public void occupied() {
		occupied = true;
		setColor(Color.GREEN);
	}

	public boolean isOccupied() {
		return occupied;
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
	}
}
