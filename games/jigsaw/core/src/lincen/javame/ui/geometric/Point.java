package lincen.javame.ui.geometric;

/**
 * 
 * @author 
 */
public class Point {
	
	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * 
	 */
	private int x;
	
	/**
	 * 
	 */
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	void setLocation(int x, int y){
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * ú
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * ú
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
