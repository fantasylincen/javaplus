package cn.javaplus.excel;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;

/**
 * 普通工作簿
 * @author 	林岑
 * @since	2012年3月23日 11:23:10
 */
class NormalSheet implements LemonSheet {

	private static final char COL_START = 'A';

	private Sheet sheet;

	public NormalSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public boolean isNothing(int row, char col) {
		String string = getString(row, col);
		return string == null || string.isEmpty() || string.equals("");
	}

	@Override
	public String getString(int row, char col) {
		return this.getString(parseToInt(col), row);
	}

	@Override
	public String getString(int row, int col) {
		return this.sheet.getCell(col, row).getContents();
	}

	/**
	 * 将字符列数, 转换为整型列数
	 * @param col	字符列
	 */
	private int parseToInt(char col) {
		return col - COL_START;
	}

	@Override
	public int getInt(int row, char col) {
		return parseInt(this.getString(row, col));
	}

	@Override
	public long getLong(int row, char col) {
		return parseLong(this.getString(row, col));
	}

	@Override
	public float getFloat(int row, char col) {
		return parseFloat(this.getString(row, col));
	}

	@Override
	public double getDouble(int row, char col) {
		return parseDouble(this.getString(row, col));
	}

	@Override
	public String[] getStringArray(boolean isHorizontal, final int colOrRow, int start, int end) {

		String [] string = new String[end - start + 1];
		if(isHorizontal) {		//如果是横向获取
			for (int col = start; col <= end; col++) {
				int indexOfString = col - start;
				string[indexOfString] = this.getString(colOrRow, col);
			}
		} else {				//如果是纵向获取
			for (int row = start; row <= end; row++) {
				int indexOfString = row - start;
				string[indexOfString] = this.getString(row, colOrRow);
			}
		}

		return string;
	}


	@Override
	public String[][] autoGet(){
		int width = getFirstRowWidth();
		return getRectArray(0, width - 1, 0);
	}

	private String[][] getRectArray(int colStart, int colEnd, int rowStart) {
		List<String[]> ls = new ArrayList<String[]>();

		while(true) {
			String[] stringArray;

			try {
				stringArray = getStringArray(true, rowStart, colStart, colEnd);
			} catch (Exception e) {
				break;
			}

			if(isSomeEmpty(stringArray)) {
				break;
			} else {
				ls.add(stringArray);
			}
			rowStart ++;
		}

		String [] [] s = new String [ls.size()] [colEnd - colStart + 1];

		for (int i = 0; i < s.length; i++) {
			s[i] = ls.get(i);
		}
		return s;
	}


	private boolean isSomeEmpty(String[] stringArray) {
		for (String string : stringArray) {
			if(string.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取第一列的宽度
	 * @return
	 */
	private int getFirstRowWidth() {
		for (int i = 0; ; i++) {

			try {
				String s = getString(0, i);
				if(s.isEmpty()) {
					return i;
				}
			} catch (Exception e) {
				return i;
			}
		}
	}

	@Override
	public int[] getIntArray(boolean isHorizontal, final int colOrRow, int start, int end) {
		String [] string = this.getStringArray(isHorizontal, colOrRow, start, end);
		int [] is = new int[string.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = parseInt(string[i]);
		}
		return is;
	}

	@Override
	public long[] getLongArray(boolean isHorizontal, final int colOrRow, int start, int end) {
		String [] string = this.getStringArray(isHorizontal, colOrRow, start, end);
		long [] is = new long[string.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = parseLong(string[i]);
		}
		return is;
	}

	@Override
	public float[] getFloatArray(boolean isHorizontal, final int colOrRow, int start, int end) {
		String [] string = this.getStringArray(isHorizontal, colOrRow, start, end);
		float [] is = new float[string.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = parseFloat(string[i]);
		}
		return is;
	}

	@Override
	public double[] getDoubleArray(boolean isHorizontal, final int colOrRow, int start, int end) {
		String [] string = this.getStringArray(isHorizontal, colOrRow, start, end);
		double [] is = new double[string.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = parseDouble(string[i]);
		}
		return is;
	}

	@Override
	public String[][] getStringArray(int startRow, int endRow, char startCol, char endCol) {
		String [][] string = new String[endRow - startRow + 1][endCol - startCol + 1];
		for (int i = 0; i < string.length; i++) {
			string[i] = this.getStringArray(true, startRow + i, parseToInt(startCol), parseToInt(endCol));
		}
		return string;
	}

	@Override
	public int[][] getIntArray(int startRow, int endRow, char startCol, char endCol) {
		String [] [] s = this.getStringArray(startRow, endRow, startCol, endCol);
		if(s.length == 0) {
			return new int[0][0];
		} else {
			int height = s.length;
			int width = s[0].length;
			int [][] is = new int[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					is[i][j] = parseInt(s[i][j]);
				}
			}
			return is;
		}

	}

	@Override
	public long[][] getLongArray(int startRow, int endRow, char startCol, char endCol) {
		String [] [] s = this.getStringArray(startRow, endRow, startCol, endCol);
		if(s.length == 0) {
			return new long[0][0];
		} else {
			int height = s.length;
			int width = s[0].length;
			long [][] is = new long[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					is[i][j] = parseLong(s[i][j]);
				}
			}
			return is;
		}
	}

	@Override
	public float[][] getFloatArray(int startRow, int endRow, char startCol, char endCol) {

		String [] [] s = this.getStringArray(startRow, endRow, startCol, endCol);
		if(s.length == 0) {
			return new float[0][0];
		} else {
			int height = s.length;
			int width = s[0].length;
			float [][] is = new float[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					is[i][j] = parseFloat(s[i][j]);
				}
			}
			return is;
		}
	}

	@Override
	public double[][] getDoubleArray(int startRow, int endRow, char startCol, char endCol) {

		String [] [] s = this.getStringArray(startRow, endRow, startCol, endCol);
		if(s.length == 0) {
			return new double[0][0];
		} else {
			int height = s.length;
			int width = s[0].length;
			double [][] is = new double [height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					is[i][j] = parseDouble(s[i][j]);
				}
			}
			return is;
		}
	}

	@Override
	public String[][] getStringArray(int colStart, int colEnd, int rowStart) {
		List<String[]> ls = new ArrayList<String[]>();

		while(true) {
			String[] stringArray = getStringArray(true, rowStart, colStart, colEnd);
			if(isAllEmpty(stringArray)) {
				break;
			} else {
				ls.add(stringArray);
			}
			rowStart ++;
		}

		String [] [] s = new String [ls.size()] [colEnd - colStart + 1];

		for (int i = 0; i < s.length; i++) {
			s[i] = ls.get(i);
		}
		return s;
	}

	/**
	 * 判断是否全是空
	 * @param stringArray
	 * @return
	 */
	private boolean isAllEmpty(String[] stringArray) {
		for (String s : stringArray) {
			if(!s.isEmpty()) {
				return false;
			}
		}
		return true;
	}


	@Override
	public String[][] autoGet2() {
		Rectangle r = findRect();
		String[][] rr = getRect(r);
		return rr;
	}

	private String[][] getRect(Rectangle r) {
		String [] [] s = new String[r.height][r.width];
		for (int row = 0; row < r.height; row++) {
			for (int col = 0; col < r.width; col++) {
				s[row][col] = getString(row, col);
			}
		}
		return s;
	}

	private Rectangle findRect() {

		Rectangle r = new Rectangle();

		try {

			while(true) {

				String text = getString(0, r.width);

				if(text.trim().isEmpty()) {

					break;
				}

				r.width++;
			}


		} catch (Exception e) {

		}

		try {
			while(true) {

				getString(r.height, 0);
				if(isEmptyAll(r.height, r.width)) {//如果行全空， 也停止循环
					break;
				}
				r.height++;
			}
		} catch (Exception e) {
		}
		return r;
	}

	/**
	 * 指定行，指定宽度的所有格子是否全部为空
	 * @param row
	 * @param width
	 * @return
	 */
	private boolean isEmptyAll(int row, int width) {
		for (int col = 0; col < width; col++) {
			String s = getString(row, col);
			if(!s.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private class Rectangle {
		private int width;
		private int height;

		@Override
		public String toString() {
			return width + ", " + height;
		};
	}

	//	@Override
	//	public String[][] autoGet2() {
	//		Info info = getRect(MAX_ROW, MAX_COL);
	//		return info.getMaxRect();
	//	}

	//	private Info getRect(int maxRow, int maxCol) {
	//		Info info = new Info();
	//		for (int row = 0; row < MAX_ROW; row++) {
	//			for (int col = 0; col < MAX_COL; col++) {
	//				String text = getString(row, col);
	//				info.content[row][col] = text;
	//
	//				if(text != null && !text.isEmpty()) {
	//					if(col > info.maxCol) {
	//						info.maxCol = col;
	//					}
	//					if(row > info.maxRow) {
	//						info.maxRow = row;
	//					}
	//				}
	//			}
	//		}
	//		return info;
	//	}
	//
	//	private class Info {
	//		private String [] [] content = new String[MAX_ROW][MAX_COL];
	//		private int maxRow;
	//		private int maxCol;
	//
	//		/**
	//		 * 获得最大矩形区数据
	//		 * @return
	//		 */
	//		public String[][] getMaxRect() {	//拷贝
	//			String [] [] s = new String [maxRow][maxCol];
	//			for (int row = 0; row < s.length; row++) {
	//				for (int col = 0; col < s.length; col++) {
	//					s[row][col] = content[row][col];
	//				}
	//			}
	//			return s;
	//		}
	//	}
}
