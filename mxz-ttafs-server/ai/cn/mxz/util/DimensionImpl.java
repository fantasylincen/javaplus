package cn.mxz.util;



public class DimensionImpl implements Dimension {
	
	private int width;

	private int height;
	
	public DimensionImpl(int width, int height) {	
		this.width = width;
		this.height = height;
	}
	/* (non-Javadoc)
	 * @see cn.mxz.util.IDimension#getWidth()
	 */
	@Override
	public int getWidth() {
		return width;
	}
	/* (non-Javadoc)
	 * @see cn.mxz.util.IDimension#getHeight()
	 */
	@Override
	public int getHeight() {
		return height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
