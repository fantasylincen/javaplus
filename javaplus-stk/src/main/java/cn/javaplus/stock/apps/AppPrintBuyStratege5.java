package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.util.Util;

public class AppPrintBuyStratege5 {

	private int buySuccessTimes;
	private int sellSuccessTimes;

	private double profit = 1;

	public static void main(String[] args) {
		while (true) {
			new AppPrintBuyStratege5().run();
		}
	}

	private void run() {
		StockMarket instance = StockMarket.getInstance();
		List<Stock2> ss = instance.getStocks();
		for (Stock2 s : ss) {
			List<DayData> datas = s.getDayDatas();

			buy(datas);
		}

		Log.d("profit", getProfit(), sellSuccessTimes, buySuccessTimes,
				(sellSuccessTimes + 0d) / buySuccessTimes);
		Util.Thread.sleep(500);
	}

	private String getProfit() {
		if (profit < -1000000) {
			return "[#-100W]";
		}
		if (profit < -10000) {
			return "[#" + Util.Str.fillSpace((int) profit + "", 5) + "]";
		}
		if (profit < 0) {
			return "[#-1   ]";
		}
		if (profit < 10000) {
			return "[" + Util.Str.fillSpace((int) profit + "", 6) + "]";
		}
		if (profit < 1000000) {
			return "[100W  ]";
		}
		if (profit < 10000000) {
			return "[1000W ]";
		}
		if (profit < 100000000) {
			return "[1Y    ]";
		}
		if (profit < 1000000000) {
			return "[10Y   ]";
		}
		if (profit < 10000000000L) {
			return "[100Y  ]";
		}
		if (profit < 100000000000L) {
			return "[1000Y ]";
		}
		if (profit < 1000000000000L) {
			return "[10000Y]";
		}
		if (profit < 10000000000000L) {
			return "[1 W Y ]";
		}
		return "VERY_XXX";
	}

	private void buy(List<DayData> datas) {
		for (DayData dt : datas) {
			boolean b = b(dt);
			// if (b) {
			if (Util.Random.isHappen(0.03))
				buy(dt);
			// }
		}
	}

	private boolean b(DayData dt) {

		double maxMacdAbs = dt.getMaxMacdAbs(50);
		double macd = Math.abs(dt.getMacd());
		boolean b = dt.isMacdGreen() && macd / maxMacdAbs < 0.1;
		return b;
	}

	private void buy(DayData dt) {
		int day = 7;
		if (dt.getTommorrow(day) == null)
			return;

		if (dt.isDieTing() || dt.isZhangTing()) {
			return;
		}

		double open = dt.getOpen();
		double buyPrice = open * 0.93;

		DayData temp = dt;
		if (buyPrice > dt.getLow()) {
			buySuccessTimes++;

			double sellPercent = 1.03;

			double sellPrice = buyPrice * sellPercent;

			for (int i = 0; i < day; i++) {
				temp = temp.getTommorrow();
				if (containsIn(temp, sellPrice)) {
					sellSuccessTimes++;
					profit *= sellPercent;
					return;
				}
			}
			double c = temp.getClose();
			double peiLv = (c - buyPrice) / buyPrice;
			profit *= (1 + peiLv);
			
//			Log.d(profit);
			if (peiLv < -1)
				throw new RuntimeException("e");
		}
	}

	private boolean containsIn(DayData t, double sellPrice) {
		return t.getHigh() > sellPrice && sellPrice > t.getLow();
	}

}
