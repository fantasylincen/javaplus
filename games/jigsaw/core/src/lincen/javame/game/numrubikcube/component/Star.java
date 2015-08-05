package lincen.javame.game.numrubikcube.component;

import java.util.Random;

import lincen.javame.game.numrubikcube.canvas.PaintToKarelCanvasAble;
import lincen.javame.ui.geometric.Point;
import lincen.javame.util.Pen;

public class Star extends Point implements PaintToKarelCanvasAble{

	private int size;
	
	public Star(int x, int y) {
		super(x, y);
		this.setSize(new Random().nextInt(3) + 2);
	}

	public void paint(Pen g) {
		int color = g.getColor();
		g.setColor(-1);
		g.drawLine(this.getX(), this.getY() + size / 2, this.getX() + size, this.getY() + size / 2);
		g.drawLine(this.getX() + size / 2, this.getY(), this.getX() + size / 2, this.getY() + size);
		g.setColor(color);
	}

	public void setSize(int size) {
		this.size = size;
	}
}
