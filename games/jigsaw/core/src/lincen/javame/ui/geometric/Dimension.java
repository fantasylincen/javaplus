package lincen.javame.ui.geometric;

/**
 * С
 * @author 
 */
class Dimension {
	
	/**
	 * 
	 */
	private int width;
	
	/**
	 * ߶
	 */
	private int height;

	/**
	 * ôС
	 * @param width
	 * @param height
	 */
	void setSize(int width, int height){
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * ÿ
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * ø߶
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * ÿ
	 * @param width
	 */
	public void setWidth(int width) {
		if(width<0){
			this.width = 0;
		}
		this.width = width;
	}

	/**
	 * ø߶
	 * @param height
	 */
	public void setHeight(int height) {
		if(height<0){
			this.height = 0;
		}
		this.height = height;
	}
}
