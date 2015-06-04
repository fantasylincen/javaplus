package cn.javaplus.stock.moni;

import cn.javaplus.stock.apps.AppPrintBuyStratege4;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;

public class StockRecommend implements Comparable<IStockRecommend>, IStockRecommend {

	/** 短期跌幅权重 */
	private static final int UP_SHORT_WEIGHT = 120;
	/** MACD 上一红色段周期内 最高价 与今日跌幅权重 */
	private static final int UP_TO_HIGH_WEIGHT = 100;
	/** 短期跌幅权重 */
	private static final int GREEN_COUNT_WEIGHT = 30;

	private final Stock2 stock;

	private Integer priority;

	public StockRecommend(Stock2 stock) {
		this.stock = stock;
	}

	@Override
	public DayData getCurrentDay() {
		int date = StockMarket.getInstance().getDate();
		return stock.getByDate(date + "");
	}

	private int calcPriority() {
		return stratege1();
	}

	private int stratege3() {
		DayData day = getCurrentDay();
		if (day == null)
			return -1;

		double macd = day.getMacd();
		if (macd > 0)
			return -1;

		int greenCount = day.getMacdGreenCountBefore();
		double dieFuToHigh = getUpToHigh(day) * -1 * 2000;// 今日到上一周期
															// macd红线区域最高价涨幅,
															// 长期下跌幅度
		double dieFuShort = getUpShort(day) * -1 * 2000;// 短期下跌幅度

		int gc = greenCount * GREEN_COUNT_WEIGHT;
		
		gc = 0;
		return (int) (gc + dieFuToHigh * UP_TO_HIGH_WEIGHT + dieFuShort
				* UP_SHORT_WEIGHT);
	}

	/**
	 * 短期涨幅
	 */
	private double getUpShort(DayData day) {
		double max4 = day.getMax4();
		double buyPrice = Market.getBuyPrice(day);
		return (buyPrice - max4) / max4;
	}

	/**
	 * 今日到上一周期 macd红线区域最高价涨幅, 长期涨幅
	 */
	private double getUpToHigh(DayData day) {
		DayData data = getPrevoursRedMacdDay(day);
		if (data == null) {
			return 1000000;
		}

		double maxPrice = 0;
		while (true) {
			if (data.getHigh() > maxPrice)
				maxPrice = data.getHigh();
			data = data.getYestoday();
			if (data == null)
				break;
			if (data.isMacdGreen())
				break;
		}

		double buyPrice = Market.getBuyPrice(day);
		return (buyPrice - maxPrice) / maxPrice;
	}

	private DayData getPrevoursRedMacdDay(DayData day) {
		while (true) {
			day = day.getYestoday();
			if (day == null)
				break;
			if (!day.isMacdGreen())
				return day;
		}
		return null;
	}

	int stratege2() {
		DayData day = getCurrentDay();
		if (day == null)
			return -1;
		int greenCount = day.getMacdGreenCountBefore();
		if (greenCount == AppPrintBuyStratege4.WITCH_GREEN_MACD_BUY - 1
				&& day.isMacdGreen())
			return 1;
		return -1;
	}

	int stratege1() {
		DayData day = getCurrentDay();
		if (day == null)
			return -1;
		if (!day.isMacdGreen())
			return -1;
		if (day.isDieTing())
			return -1;
		int greenCount = day.getMacdGreenCountBefore();

		if (greenCount < 2) {
			return -1;
		}

		DayData secondGreenMacd = getSecondGreenMacd(day); // 第二根绿色macd对应数据

		double up = getUp(day, secondGreenMacd);

		return (int) (up * -1000);
	}

	private DayData getSecondGreenMacd(DayData day) {
		int greenCount = day.getMacdGreenCountBefore();
		int yestodayCount = greenCount - 1;
		if (yestodayCount < 1)
			throw new IllegalArgumentException();
		return day.getYestoday(yestodayCount);
	}

	/***
	 * day1 相对day2的买入价格涨幅
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	private double getUp(DayData day1, DayData day2) {
		double p1 = Market.getBuyPrice(day1);
		double p2 = Market.getBuyPrice(day2);
		return (p1 - p2) / p2;
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
