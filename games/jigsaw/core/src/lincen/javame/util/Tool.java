package lincen.javame.util;

import lincen.javame.ui.geometric.Point;

public class Tool {

	private static void fillRect(Point leftUp, Point rightDown, Pen g){
		g.fillRect(leftUp.getX(), leftUp.getY(), rightDown.getX() - leftUp.getX(), rightDown.getY() - leftUp.getY());
	}

	private static void fillTriangle(Point p1, Point p2, Point p3, Pen g){
		g.fillTriangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
	}

	public static void fillHexagon(int x, int y, int width, int height, Pen g, RGB rgb, int bcenter, int up, int down, int lu, int ld, int ru, int rd){

		final int d = 10;
		final int w = width - d;
		final int h = height -d;

		final Point p1 = new Point((int) (w * 0.732 / 3.464) + d / 2 + x, d / 2 + y);
		final Point p2 = new Point((int) (w * 2.732 / 3.464) + d / 2 + x, d / 2 + y);
		final Point p3 = new Point((int) (w * 3.732 / 3.464) + d / 2 + x, h / 2 + d / 2 + y);
		final Point p4 = new Point((int) (w * 2.732 / 3.464) + d / 2 + x, h + d / 2 + y);
		final Point p5 = new Point((int) (w * 0.732 / 3.464) + d / 2 + x, h + d / 2 + y);
		final Point p6 = new Point((int) (-w * (2 - 1.732) / 3.464) + d / 2 + x, h / 2 + d / 2 + y);

		final Point q1 = new Point(p1.getX() +4, p1.getY() + 7);
		final Point q2 = new Point(p2.getX() - 4, p2.getY() + 7 );
		final Point q3 = new Point(p3.getX() - 8, p3.getY());
		final Point q4 = new Point(p4.getX() - 4, p4.getY() - 7);
		final Point q5 = new Point(p5.getX() + 4, p5.getY() - 7 );
		final Point q6 = new Point(p6.getX() + 8, p6.getY());

		int brightnessBefor = rgb.getBrightness();
		rgb.setBrightness(bcenter);
		g.setColor(rgb.getColor());

		fillRect(q1, q4, g);

		// 
		fillTriangle(q1, q5, q6, g);

		// 
		fillTriangle(q2, q3, q4, g);


		//ϱ
		rgb.setBrightness(up);
		g.setColor(rgb.getColor());
		fillTriangle(p1, q1, p2, g);
		fillTriangle(q2, q1, p2, g);

		//
		rgb.setBrightness(down);
		g.setColor(rgb.getColor());
		fillTriangle(p5, q4, q5, g);
		fillTriangle(p5, q4, p4, g);
		
		//ϱ
		rgb.setBrightness(lu);
		g.setColor(rgb.getColor());
		fillTriangle(q1, p6, p1, g);
		fillTriangle(q1, p6, q6, g);

		//
		rgb.setBrightness(ld);
		g.setColor(rgb.getColor());
		fillTriangle(q6, p5, p6, g);
		fillTriangle(q6, p5, q5, g);
		
		//ϱ
		rgb.setBrightness(ru);
		g.setColor(rgb.getColor());
		fillTriangle(q2, p3, p2, g);
		fillTriangle(q2, q3, p3, g);
		
		//
		rgb.setBrightness(rd);
		g.setColor(rgb.getColor());
		fillTriangle(q3, p4, p3, g);
		fillTriangle(q3, p4, q4, g);

		rgb.setBrightness(brightnessBefor);
	}

	/**
	 * ƾεı߿
	 * @param g
	 */
	public static void fillBorder(Pen g, RGB rgb, int x,  int y, int w, int h) {
		final int dw = 5;
		
		//
		rgb.setBrightness(255);
		g.setColor(rgb.getColor());
		g.fillTriangle(x, y, x - dw, y - dw, x + w + dw, y - dw);
		g.fillTriangle(x, y, x + w, y, x + w + dw, y - dw);
		
		//
		rgb.setBrightness(130);
		g.setColor(rgb.getColor());
		g.fillTriangle(x, y + h, x + w + dw, y + h + dw, x + w, y + h);
		g.fillTriangle(x, y + h, x + w + dw, y + h + dw, x - dw, y + h + dw);
		
		//
		rgb.setBrightness(200);
		g.setColor(rgb.getColor());
		g.fillTriangle(x - dw, y - dw, x, y, x, y + h);
		g.fillTriangle(x - dw, y - dw, x - dw, y + h + dw, x, y + h);
		
		//
		rgb.setBrightness(180);
		g.setColor(rgb.getColor());
		g.fillTriangle(x + w, y, x + w + dw, y - dw, x + w, y + h);
		g.fillTriangle(x + w + dw, y + h + dw, x + w + dw, y - dw, x + w, y + h);
		
		rgb.setBrightness(256);
	}
}
