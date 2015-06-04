package cn.javaplus.stock.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyImage {

	private final int h;
	private final int w;
	private BufferedImage image;
	private Graphics graphics;

	public MyImage(int w, int h) {
		this.w = w;
		this.h = h;

		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		graphics = image.getGraphics();
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public void savePng(String file) {
		File f = new File(file);
		try {
			ImageIO.write(image, "png", f);
			image.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}

	public void drawLine(int x0, int y0, int x1, int y1, Color color) {
		Color old = graphics.getColor();
		graphics.setColor(color);
		graphics.drawLine(x0, y0, x1, y1);
		graphics.setColor(old);
	}

	public void fillRect(int x0, int y0, int w, int h, Color color) {
		Color old = graphics.getColor();
		graphics.setColor(color);
		graphics.fillRect(x0, y0, w, h);
		graphics.setColor(old);
	}

	public void drawString(int x, int y, String string, Color color) {
		Color old = graphics.getColor();
		graphics.setColor(color);
		graphics.drawString(string, x, y);
		graphics.setColor(old);
	}

}
