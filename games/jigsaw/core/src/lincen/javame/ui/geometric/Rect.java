package lincen.javame.ui.geometric;


/**
 * 
 * ڲϢεĺ,,,߶
 * @author 
 */
public class Rect {

	/**
	 * ε
	 */
	private Point location = new Point(0, 0);

	/**
	 * εĴС
	 */
	private Dimension size = new Dimension();

	/**
	 * ƶ(x, y)
	 * @param x
	 * @param y
	 */
	public void move(int x, int y){
		setLocation(getX() + x, getY() + y);
	}

	/**
	 * þεĺ
	 * @return
	 */
	public int getX() {
		return this.getLocation().getX();
	}

	/**
	 * þε
	 * @param x
	 */
	public int getY() {
		return this.getLocation().getY();
	}

	/**
	 * þεĿ
	 * @return
	 */
	public int getWidth() {
		return this.getSize().getWidth();
	}

	/**
	 * þεĸ߶
	 * @return
	 */
	public int getHeight() {
		return this.getSize().getHeight();
	}

	/**
	 * þε
	 * @return
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * þεĴС
	 * @return
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * ú
	 */
	public void setLocation(int x, int y){
		this.location.setLocation(x, y);
	}

	/**
	 * ôС
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height){
		this.size.setSize(width, height);
	}

	/**
	 * ԾϵΪ׼,Ƿ(x, y)
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y) {
		if(
				x>=this.getX() && x<=this.getX() + this.getWidth() && 
				y>=this.getY() && y<=this.getY() + this.getHeight()
		){
			return true;
		} else {
			return false;
		}
	}
}
