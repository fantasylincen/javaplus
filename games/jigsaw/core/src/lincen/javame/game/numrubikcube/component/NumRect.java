package lincen.javame.game.numrubikcube.component;

import lincen.javame.game.numrubikcube.canvas.PaintToKarelCanvasAble;
import lincen.javame.ui.geometric.Rect;
import lincen.javame.util.Pen;
import lincen.javame.util.RGB;
import lincen.javame.util.Tool;

public class NumRect extends Rect implements PaintToKarelCanvasAble{


	private int value;

	private int position;

	private RGB rgb = new RGB(205,80,60);

	public void setRgb(RGB rgb) {
		this.rgb = rgb;
	}

	private NumRectContainer parent;

	public void paint(Pen g) {
		fillBackground(g);
		drawNum(g);
	}

	private void drawNum(Pen g) {
	}

	/**
	 * ַı
	 * @param g
	 */
	private void fillBackground(Pen g) {
		int colorBefor = g.getColor();

		//һ
		Tool.fillHexagon(
				this.getX(), 
				this.getY(), 
				this.getWidth(), 
				this.getHeight(), 
				g, 
				this.rgb, 
				200, 
				220, 
				120, 
				230,//l u 
				180, // l d
				170, // r u
				140//r d
		);
		g.setColor(colorBefor);
	}

	/**
	 * ֵ
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * ֵ
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * þολ
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * þολ
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	public NumRectContainer getParent() {
		return parent;
	}

	public void setParent(NumRectContainer parent) {
		this.parent = parent;
	}
}
