package cn.mxz.nvwa;

public class LineItemImpl implements LineItem {

	private SplitLine line;

	public LineItemImpl(SplitLine line) {
		this.line = line;
	}

	@Override
	public int getCount() {
		return line.getCount();
	}

	@Override
	public int getZheKou() {
		return line.getZheKou();
	}

}
