package cn.javaplus.stock.moni;

import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;

public class StockRecommend2 implements IStockRecommend {

	private final Stock2 stock;

	private Integer priority;

	public StockRecommend2(Stock2 stock) {
		this.stock = stock;
	}

	@Override
	public DayData getCurrentDay() {
		int date = StockMarket.getInstance().getDate();
		return stock.getByDate(date + "");
	}

	private int calcPriority() {
		DayData currentDay = getCurrentDay();
		if (currentDay == null)
			return -1;
		if (b(currentDay)) {
			return 1;
		} else {
			return -1;
		}
	}

	private boolean b(DayData dt) {

		double maxMacdAbs = dt.getMaxMacdAbs(50);
		double macd = Math.abs(dt.getMacd());
		boolean b = dt.isMacdGreen() && macd / maxMacdAbs < 0.1;
		return b;
	}

	/**
	 * 买入优先级 值越大, 越推荐买入
	 */
	@Override
	public int getPriority() {
		if (priority == null)
			priority = calcPriority();
		return priority;
	}

	@Override
	public int compareTo(IStockRecommend o) {
		return o.getPriority() - getPriority();
	}

}
