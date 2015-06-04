package cn.javaplus.excel;

/**
 * Excel工作表<br>
 * 这个工作表主要用来截取工作表中的某个区域的数组, 可返回int long float double String数组形式<br>
 * @author 	林岑
 * @since	2012年3月23日 11:27:54
 */
public interface LemonSheet {
	
	/**
	 * 判断指定Cell是否为空
	 * @param row
	 * @param col			从'A'开始
	 */
	public boolean isNothing(int row, char col);

	/**
	 * 获取第row 行 col 列格子 的 int 值
	 * @param row			行数 从0开始
	 * @param col			列数 从'A' 开始, 注意是大写
	 */
	public abstract String getString(int row, char col);
	public abstract String getString(int row, int col);
	public abstract int getInt(int row, char col);
	public abstract long getLong(int row, char col);
	public abstract float getFloat(int row, char col);
	public abstract double getDouble(int row, char col);
	
	
	
	/**
	 * 将一个范围内的Cell转换成 一个一维数组
	 * @param isHorizontal	是否横向获取, 如果不是, 那么就是纵向(范围内不能有空)
	 * @param colOrRow		行或者列数
	 * @param start			开始位置		从零开始
	 * @param end			结束位置		从零开始
	 * @return				范围内的数组
	 */
	public abstract String [] getStringArray(boolean isHorizontal, int colOrRow, int start, int end);
	public abstract int [] getIntArray(boolean isHorizontal, int colOrRow, int start, int end);
	public abstract long [] getLongArray(boolean isHorizontal, int colOrRow, int start, int end);
	public abstract float [] getFloatArray(boolean isHorizontal, int colOrRow, int start, int end);
	public abstract double [] getDoubleArray(boolean isHorizontal, int colOrRow, int start, int end);
	
	
	/**
	 * 将一个范围内Cell 转换为二维数组
	 * @param startRow		开始行
	 * @param endRow		结束行
	 * @param startCol		开始列(A开始)
	 * @param endCol		结束列(A开始)
	 * @return
	 */
	public abstract String [] [] getStringArray(int startRow, int endRow, char startCol, char endCol);
	public abstract int [] [] getIntArray(int startRow, int endRow, char startCol, char endCol);
	public abstract long [] [] getLongArray(int startRow, int endRow, char startCol, char endCol);
	public abstract float [] [] getFloatArray(int startRow, int endRow, char startCol, char endCol);
	public abstract double [] [] getDoubleArray(int startRow, int endRow, char startCol, char endCol);

	/**
	 * 自动获取所有内容, 从rowStart开始检测, 一旦有空行就停止
	 * @param colStart	开始列 0 开始
	 * @param colEnd	结束列 从0开始
	 * @param rowStart	开始行 从1开始
	 * @return
	 */
	public String[][] getStringArray(int colStart, int colEnd, int rowStart);

	/**
	 * 自动获取最大矩形区域所有字符串
	 * 
	 * 最大矩形区域:	该矩形区域中不能有任意一个格子为空
	 * 
	 * 获取方式: 以第一列宽度为基准, 向下扫描, 一旦发现空格就停止
	 * 
	 * @return
	 */
	String[][] autoGet();
	
	/**
	 * 自动获取最大矩形区域所有字符串
	 * 
	 * 最大矩形区域:	单元格可以为空
	 * 
	 * 获取方式: 以一个最小的矩形框， 框住所有不为空的格子
	 * 
	 * @return
	 */
	String[][] autoGet2();
}
