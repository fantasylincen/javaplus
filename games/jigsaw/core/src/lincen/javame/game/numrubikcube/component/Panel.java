package lincen.javame.game.numrubikcube.component;

import lincen.javame.game.numrubikcube.canvas.PaintToKarelCanvasAble;
import lincen.javame.ui.geometric.Rect;
import lincen.javame.util.Pen;

public class Panel extends Rect implements PaintToKarelCanvasAble{

	public void paint(Pen g) {
		int colorBefor = g.getColor();
		g.setColor(0x111111);
		g.fillRoundRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), 15, 15);
		g.setColor(colorBefor);
	}

}
