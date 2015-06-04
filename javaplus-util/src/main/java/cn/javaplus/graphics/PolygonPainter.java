package cn.javaplus.graphics;

import java.util.List;

public class PolygonPainter {

	private Polygon	shape;

	public PolygonPainter(Polygon shape) {
		this.shape = shape;
	}

	public void paint(IGraphics g) {
		List<Line> ls = shape.getLines();
		for (Line line : ls) {
			g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
		}
	}

}
