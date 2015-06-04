package cn.javaplus.graphics;

import java.util.List;

import cn.javaplus.collections.list.Lists;

public class PolygonImpl implements Polygon {

	private final class MyLine implements Line {
		private final float	y1;
		private final float	x1;
		private final float	x2;
		private final float	y2;

		private MyLine(float y1, float x1, float x2, float y2) {
			this.y1 = y1;
			this.x1 = x1;
			this.x2 = x2;
			this.y2 = y2;
		}

		@Override
		public float getY2() {
			return y2;
		}

		@Override
		public float getY1() {
			return y1;
		}

		@Override
		public float getX2() {
			return x2;
		}

		@Override
		public float getX1() {
			return x1;
		}

		@Override
		public String toString() {
			return "MyLine [y1=" + y1 + ", x1=" + x1 + ", x2=" + x2 + ", y2=" + y2 + "]";
		}
		
		
	}

	private Vector2D[]	vertices;
	private List<Line>	lines;

	public PolygonImpl(Vector2D[] vertices) {
		if (vertices.length < 3) {
			throw new IllegalArgumentException("Point size must >= 3!");
		}
		this.vertices = vertices;
		initLines();
	}

	private void initLines() {

		lines = Lists.newArrayList();
		for (int i = 0; i < vertices.length - 1; i++) {
			final float x1 = vertices[i].getX();
			final float y1 = vertices[i].getY();
			final float x2 = vertices[i + 1].getX();
			final float y2 = vertices[i + 1].getY();

			lines.add(new MyLine(y1, x1, x2, y2));
		}

		final float x2 = vertices[0].getX();
		final float y2 = vertices[0].getY();
		final float x1 = vertices[vertices.length - 1].getX();
		final float y1 = vertices[vertices.length - 1].getY();
		lines.add(new MyLine(y1, x1, x2, y2));
	}

	@Override
	public List<Line> getLines() {
		return lines;
	}

}
