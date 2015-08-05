package cn.javaplus.game.power.stage;

public class Grid {

	private int xIndex;
	private int yIndex;
	private int v;

	public Grid(int xIndex, int yIndex, int v) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.v = v;
	}

	public int getV() {
		return v;
	}
	
	public int getXIndex() {
		return xIndex;
	}
	public int getYIndex() {
		return yIndex;
	}
}
