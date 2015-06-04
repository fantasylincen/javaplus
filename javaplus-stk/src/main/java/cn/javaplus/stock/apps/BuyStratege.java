package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.ImageSaver;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class BuyStratege {

	/**
	 * macd 变红后 几日强制卖出
	 */
	private int sellDayAfterRed;

	static List<Stock2> stocks = Market.loadRandomStocks(AppPrintBuyStratege3.STOCK_COUNT);

	public void setDateStart(int dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(int dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "BuyStratege [witchGreenMacdLineToBuy="
				+ witchGreenMacdLineToBuy + ", sellDayAfterRed="
				+ sellDayAfterRed + ", dateStart=" + dateStart + ", dateEnd="
				+ dateEnd + "]";
	}

	public void setSellDayAfterRed(int sellDayAfterRed) {
		this.sellDayAfterRed = sellDayAfterRed;
	}

	/**
	 * 盈利结果
	 */
	List<Profit> profits = Lists.newArrayList();

	private int dateStart;

	private int dateEnd;

	private int witchGreenMacdLineToBuy;

	public List<Profit> run() {
		for (Stock2 s : stocks) {
			tryToBuy(s);
		}
		return profits;
	}

	private void tryToBuy(Stock2 s) {
		List<DayData> datas = s.getDayDatas();
		for (DayData day : datas) {

			if (!isInScope(day)) {
				continue;
			}

			if (!Util.Random.isHappen(AppPrintBuyStratege3.BUY_PROBABILITY)) {
				continue;
			}
			
			if (confirm(day)) {

				double buyPrice = Market.getBuyPrice(day);

				DayData sellDay = getSellDay(day);
				if (sellDay == null)
					continue;

				double sellPrice = Market.getSellPrice(sellDay);

				double profit = (sellPrice - buyPrice) / buyPrice;

				int spaceDay = Market.getSpaceDay(sellDay, day);

				Profit p = new Profit();
				p.setProfit(profit);
				p.setSpaceDay(spaceDay);
				profits.add(p);

				double pp = AppPrintBuyStratege3.GENERATE_IMAGE_PROBABILITY;
				if (Util.Random.isHappen(pp)) {
					new ImageSaver().saveToImage(day, sellDay);
				}
				// Log.d(s.getId(), "buy", day.getDate(),
				// Market.toPriceString(buyPrice), sellDay.getDate(),
				// Market.toPriceString(sellPrice), profit);
			}

		}
	}

	/**
	 * 是否在指定时间范围内?
	 * 
	 * @param day
	 * @return
	 */
	private boolean isInScope(DayData day) {
		if (dateStart != 0 && day.getDateInt() < dateStart) {
			return false;
		}
		if (dateEnd != 0 && dateEnd < day.getDateInt())
			return false;

		return true;
	}

	/**
	 * 符合买入条件
	 */
	private boolean confirm(DayData day) {
		if (day.getUp() >= 0.095)
			return false;

//		if (isLowerThan(witchGreenMacdLineToBuy, day)) {
//			return true;
//		}

		int count = day.getMacdGreenCountBefore();
		return count == witchGreenMacdLineToBuy - 1 && day.isMacdGreen();
	}

	/**
	 * day 前所有绿色macd线中, 第二根绿色macd对应 的买价 A
	 * 
	 * 返回 day的买价 是否小于A
	 * 
	 * @param witchGreenMacdLineToBuy
	 * @param day
	 * @return
	 */
	private boolean isLowerThan(int witchGreenMacdLineToBuy, DayData day) {
		int count = day.getMacdGreenCountBefore();
		int before = count - witchGreenMacdLineToBuy + 1;
		DayData d = day.getYestoday(before);
		double buyPrice = Market.getBuyPrice(d);
		return Market.getBuyPrice(day) < buyPrice;
	}

	private DayData getSellDay(DayData day) {
		day = getFirstRedMacdDay(day);

		day = getTomorrow(day, 1);

		return getNotDieTing(day);
	}

	private DayData getTomorrow(DayData day, int count) {
		if (day == null)
			return null;
		return day.getTommorrow(count);
	}

	/**
	 * 如果今日跌停了, 获取其后第一个非跌停的交易日
	 * 
	 * @param day
	 * @return
	 */
	private DayData getNotDieTing(DayData day) {
		while (true) {
			if (day == null)
				return null;
			double up = day.getUp();
			if (up > -0.095) {
				return day;
			}

			day = day.getTommorrow();
		}
	}

	private DayData getFirstRedMacdDay(DayData day) {
		while (day.getMacd() <= 0) {
			day = day.getTommorrow();
			if (day == null)
				return null;
		}
		return day;
	}

	public void setDateScope(int dateStart, int dateEnd) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;

	}

	public int getDateStart() {
		return dateStart;
	}

	public int getDateEnd() {
		return dateEnd;
	}

	/**
	 * 第几根macd绿线买入
	 */
	public void setWitchGreenMacdLineToBuy(int witchGreenMacdLineToBuy) {
		this.witchGreenMacdLineToBuy = witchGreenMacdLineToBuy;
	}

}
