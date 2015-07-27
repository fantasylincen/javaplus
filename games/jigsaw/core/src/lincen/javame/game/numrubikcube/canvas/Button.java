package lincen.javame.game.numrubikcube.canvas;

public interface Button extends PaintToKarelCanvasAble {

	void setText(String string);

	void setSize(float w, float h);

	float getWidth();

	float getHeight();

	void setLocation(float x, float y);

	boolean contains(float x, float y);

}
