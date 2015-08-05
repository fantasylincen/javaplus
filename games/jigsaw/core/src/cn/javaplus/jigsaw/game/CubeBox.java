package cn.javaplus.jigsaw.game;

/**
 * 用于存放cube的容器
 * 
 * @author 林岑
 * 
 */
public class CubeBox {

	private int rowIndex;
	private int colIndex;
	private float x;
	private float y;
	private Cube cube;

	public CubeBox(int rowIndex, int colIndex, float x, float y) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.x = x;
		this.y = y;
	}

	public int getColIndex() {
		return colIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Cube getCube() {
		return cube;
	}

	public void put(Cube cube) {
		this.cube = cube;
		if (cube != null) {
			cube.setPosition(getX(), getY());
			cube.setBox(this);
		}
	}

	public void swap(CubeBox box) {
		Cube cube = box.getCube();
		Cube myCube = getCube();
		box.put(myCube);
		put(cube);
	}

	@Override
	public String toString() {
		return "r:" + rowIndex + " c:" + colIndex + " cube:" + cube;
	}
}
