package lincen.javame.util;

public interface Pen {

	void setColor(int color);

	void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

	void fillRect(int x, int y, int w, int h);

	int getColor();

	void drawLine(int x1, int y1, int x2, int y2);

	void fillRoundRect(int x, int y, int width, int height, int a1, int a2);

	void translate(int x, int y);

}
