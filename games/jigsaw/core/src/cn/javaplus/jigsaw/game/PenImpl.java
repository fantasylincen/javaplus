package cn.javaplus.jigsaw.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import lincen.javame.util.Pen;
import lincen.javame.util.RGB;

public class PenImpl implements Pen {

	private Batch batch;
	private ShapeRenderer render;
	private int color;
	int x;
	int y;

	public PenImpl(Batch batch) {
		this.batch = batch;
		render = new ShapeRenderer();
	}

	@Override
	public void setColor(int color) {
		this.color = color;
		RGB r = new RGB(0, 0, 0);
		r.setColor(color);

		float m = 256f;
		Color c = new Color(r.getR() / m, r.getG() / m, r.getB() / m, 1);

		batch.setColor(c);
	}

	@Override
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		render.begin(ShapeType.Filled);
		render.triangle(x1 + x, y1 + y, x2 + x, y2 + y, x3 + x, y3 + y);
		render.end();
	}

	@Override
	public void fillRect(int x, int y, int w, int h) {
		render.begin(ShapeType.Filled);
		render.rect(x + this.x, y + this.y, w, h);
		render.end();
	}

	@Override
	public int getColor() {
		return color;
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		render.begin(ShapeType.Line);
		render.line(x1 + x, y1 + y, x2 + x, y2 + y);
		render.end();
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height, int a1,
			int a2) {
		render.begin(ShapeType.Filled);
		render.rect(x + this.x, y + this.y, width, height);
		render.end();

	}

	@Override
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}

}
