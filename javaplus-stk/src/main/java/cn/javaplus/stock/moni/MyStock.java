package cn.javaplus.stock.moni;

import cn.javaplus.stock.apps.AppPrintBuyStratege6;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class MyStock {

	public class Sell {
		private double price;

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public int getCount() {
			return count;
		}

		public double getAllPrice() {
			return price * count;
		}

	}

	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	double singlePrice;
	int count;
	private int buyDate;
	private DayData buyDay;

	public int getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(int buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * 当日总价
	 */
	public double getCurrentPriceAll() {
		StockMarket instance = StockMarket.getInstance();
		double price = instance.getPrice(id);
		int cnt = getCount();
		return price * cnt;
	}

	/**
	 * 总盈利
	 */
	public double getProfitAll() {
		double costPrice = getSinglePrice() * getCount();
		double currentPrice = getCurrentPriceAll();
		return (currentPrice - costPrice) / costPrice;
	}

	/**
	 * 今日数据 可能为null
	 */
	public DayData getToday() {
		StockMarket instance = StockMarket.getInstance();
		Stock2 s = instance.getStock(id);
		int date = instance.getDate();
		DayData data = s.getByDate(date + "");
		return data;
	}

	public DayData getBuyDay() {
		return buyDay;
	}

	public void setBuyDay(DayData buyDay) {
		this.buyDay = buyDay;
	}

	public boolean isTooManyDays() {
		DayData cd = getToday();
		DayData bd = getBuyDay();
		int space = Market.getSpace(cd, bd);
		return space >= AppPrintBuyStratege6.MAX_SELL_DAY;
	}

	public Sell getSell() {

		Sell sell = new Sell();

		DayData today = getToday();

		if (today == null)
			return sell;

		if (today.isDieTing() || today.isZhangTing()) {
			return sell;
		}

//		if (isTooManyDays()) {
//			DayData yestoday = today.getYestoday();
//			double rd = getRandomPrice(yestoday);
//
//			if (rd > yestoday.getClose())
//				sell.setPrice(rd);
//			return sell;
//		}

		double buyPrice = getSinglePrice();

		double randomPrice = getRandomPrice(today);

//		if (randomPrice / buyPrice > 0.0) {
			sell.setPrice(randomPrice);
//		}

		return sell;

	}

	private double getRandomPrice(DayData today) {
		double high = today.getHigh();
		double randomPrice = Util.Random.get(today.getLow(), high);
		return randomPrice;
	}

	// private double getSellPrice(MyStock s, double sellCondition) {
	//
	// DayData day = s.getToday();
	// double open = day.getOpen();
	//
	// if (s.isTooManyDays())
	// return open;
	//
	// double sprice = s.getSinglePrice();
	//
	// double openSPercent = open / sprice;
	// if (openSPercent > sellCondition) {
	// return open;
	// }
	//
	// double p = sprice * sellCondition;
	// return p;
	// }
}
