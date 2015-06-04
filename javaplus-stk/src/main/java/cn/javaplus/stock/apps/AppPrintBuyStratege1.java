package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.stock.util.Market;

/**
 * 60 ri zui di jia mai ru
 */
public class AppPrintBuyStratege1 {

	/**
	 * N ri zui di jia
	 */
	private static final int DAY = 60;

	/**
	 * chi you tian shu
	 */
	private static final int NEXT = 31;

	static Avg avg = new Avg();
	
	static List<Stock1> ALL = readAll();

	private static List<Stock1> readAll() {
		List<Stock1> all = new StockReader().readShenHuA();
		return all;
	}

	public static void main(String[] args) {
		for (int i = 1996; i < 2016; i++) {
			String year = i + "";
			print(year);
		}
	}

	private static void print(String year) {
		for (Stock1 stock : ALL) {
			print(stock.getId(), stock.getDayDatas(year));
		}

		if (avg.getCount() == 0)
			return;

		Log.d(year, "年");

		Log.d("只要在" + DAY + "日最低价, 就买入");
		Log.d("买入" + NEXT + "天强制平仓");
		Log.d("卖价与买价平均比值", avg.getValue());
		Log.d("统计量" + avg.getCount());
		double k = avg.getValue() - 1;
		Log.d(NEXT + "日盈利", k * 100 + "%");
		Log.d("年化收益率", (k * 200 / NEXT) * 100 + "%");
		Log.d("");
		Log.d("");
		Log.d("");
		avg.reset();
	}

	private static void print(String id, List<OneDayData> prices) {

		for (int i = DAY + 10; i < prices.size(); i++) {

			OneDayData p = prices.get(i);

			if (isLowest(p, DAY) && hasNextDay(p, NEXT)) {
				double buyPrice = p.getAvg();
				OneDayData sell = getNextPriceAvg(p, NEXT);
				double sellPrice = sell.getAvg();

				String sellPri = Market.toPriceString(sellPrice);
				String buyPri = Market.toPriceString(buyPrice);
				String s = sellPrice > buyPrice ? "O" : "X";
				// Log.d(stock.getId(), s, p.getDate(), buyPri, sell.getDate(),
				// sellPri);
				double up = sellPrice / buyPrice;
				avg.add(up);
			}
		}

	}

	private static double getZhangFu(OneDayData p3, OneDayData p2) {
		double avg2 = p2.getAvg();
		double avg3 = p3.getAvg();
		return (avg2 - avg3) / avg3;
	}

	/**
	 * shi fou you nextDay de shuju
	 */
	private static boolean hasNextDay(OneDayData p, int nextDay) {
		for (int i = 0; i < nextDay; i++) {
			p = p.getTomorrow();
			if (p == null) {
				return false;
			}
		}
		return true;
	}

	private static OneDayData getNextPriceAvg(OneDayData p, int nextDay) {

		double buyPrice = p.getAvg();

		for (int i = 0; i < nextDay; i++) {
			p = p.getTomorrow();
			double avg2 = p.getAvg();
			//
			// double d = avg2 / buyPrice;
			// if (d > 1.3) {
			// return p;
			// }
		}
		return p;
	}

	private static boolean isLowest(OneDayData p, int day) {
		double avg = p.getAvg();
		for (int i = 0; i < day; i++) {
			p = p.getYestoday();
			double a = p.getAvg();
			if (a < avg)
				return false;
		}
		return true;
	}
}
