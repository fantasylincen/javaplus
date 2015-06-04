package cn.javaplus.stock.stock;

public class TwoDaysData {

	private OneDayData before;
	private OneDayData after;

	public TwoDaysData(OneDayData before, OneDayData after) {
		this.before = before;
		this.after = after;
	}

	public OneDayData getTheDayBefore() {
		return before;
	}

	public OneDayData getTheDayAfter() {
		return after;
	}

}
