package cn.javaplus.stock.t;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.text.AttributedCharacterIterator;

public class SuperGraphics {

	private Graphics g;

	public SuperGraphics(Graphics g) {
		this.g = g;
	}

	public void drawLine(int x1, int y1, int x2, int y2, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(old);
	}

	public void fillRect(int x, int y, int width, int height, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillRect(x, y, width, height);
		g.setColor(old);
	}

	public void drawRect(int x, int y, int width, int height, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawRect(x, y, width, height);
		g.setColor(old);

	}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(old);

	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(old);

	}

	public void drawOval(int x, int y, int width, int height, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawOval(x, y, width, height);
		g.setColor(old);

	}

	public void fillOval(int x, int y, int width, int height, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillOval(x, y, width, height);
		g.setColor(old);

	}

	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillArc(x, y, width, height, startAngle, arcAngle);
		g.setColor(old);

	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawPolyline(xPoints, yPoints, nPoints);
		g.setColor(old);

	}

	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.setColor(old);

	}

	public void drawPolygon(Polygon p, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawPolygon(p);
		g.setColor(old);

	}

	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillPolygon(xPoints, yPoints, nPoints);
		g.setColor(old);

	}

	public void fillPolygon(Polygon p, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.fillPolygon(p);
		g.setColor(old);

	}

	public void drawString(String str, int x, int y, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawString(str, x, y);
		g.setColor(old);

	}

	public void drawString(AttributedCharacterIterator iterator, int x, int y, Color c) {
		Color old = g.getColor();
		g.setColor(c);
		g.drawString(iterator, x, y);
		g.setColor(old);

	}

}
