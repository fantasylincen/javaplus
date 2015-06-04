package cn.mxz.nvwa;

public class SplitLine {

	private int count;
	private int zheKou;

	public SplitLine(int count, int zheKou) {
		this.count = count;
		this.zheKou = zheKou;
	}

	/**
	 * 折扣  0 - 10
	 * @return
	 */
	public int getZheKou() {
		return zheKou;
	}

	public int getCount() {
		return count;
	}

}
