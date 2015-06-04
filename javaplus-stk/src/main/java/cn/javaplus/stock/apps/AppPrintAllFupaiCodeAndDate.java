package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.stock.stock.TwoDaysData;
import cn.javaplus.stock.util.Market;
import cn.javaplus.time.Time;

/**
 * 复牌股 以及停牌日期
 */
public class AppPrintAllFupaiCodeAndDate {

	static Avg avgLow = new Avg();
	static Avg avgHigh = new Avg();
	static Avg avgOpen = new Avg();
	static Avg avgClose = new Avg();
	public static void main(String[] args) {

		StockReader r = new StockReader();

		List<Stock1> all = r.readShenHuA();

		for (Stock1 stock : all) {

			print(stock);
		}
		Log.d("平均最低价卖出:"  +  Market.toPriceString(avgLow.getValue() * 100) + "%");
		Log.d("平均最高价卖出:"  +  Market.toPriceString(avgHigh.getValue() * 100) + "%");
		Log.d("平均开盘价卖出:"  +  Market.toPriceString(avgOpen.getValue() * 100) + "%");
		Log.d("平均收盘价卖出:"  +  Market.toPriceString(avgClose.getValue() * 100) + "%");
	}

	private static void print(Stock1 stock) {
		List<TwoDaysData> ls = stock.getTwoDaysData();
		
		
		for (TwoDaysData price : ls) {
			OneDayData before = price.getTheDayBefore();
			OneDayData after = price.getTheDayAfter();

			long b = before.getDateMillis();
			long a = after.getDateMillis();
			
			double close = before.getClose();
			double open = after.getOpen();
			
			double percent = (open - close) / close;

			if (after.getDate() > 20150101 && a - b >= Time.MILES_ONE_DAY * 40 && percent > 0.095) {
				OneDayData canBuyDay = getCanBuyDay(before);
				
				Object buyDate = "X";
				String zdLow = "X";
				String zdHigh = "X";
				String zdOpen = "X";
				String zdClose = "X";

				if(canBuyDay != null) {
					double buyPrice = canBuyDay.getHigh();
					buyDate = canBuyDay.getDate();
					OneDayData tom = canBuyDay.getTomorrow();
					if(tom != null) {
						double sellPriceLow = tom.getLow();
						double sellPriceHigh = tom.getHigh ();
						double sellPriceOpen = tom.getOpen();
						double sellPriceClose = tom.getClose();

						double percentLow = (sellPriceLow - buyPrice) / buyPrice;
						double percentHigh = (sellPriceHigh - buyPrice) / buyPrice;
						double percentOpen = (sellPriceOpen - buyPrice) / buyPrice;
						double percentClose = (sellPriceClose - buyPrice) / buyPrice;

						avgLow.add(percentLow);
						avgHigh.add(percentHigh);
						avgOpen.add(percentOpen);
						avgClose.add(percentClose);

						zdLow = Market.toPriceString(percentLow * 100) + "%";
						zdHigh = Market.toPriceString(percentHigh * 100) + "%";
						zdOpen = Market.toPriceString(percentOpen * 100) + "%";
						zdClose = Market.toPriceString(percentClose * 100) + "%";
					}
				}
				
				String cc = "买入后涨跌 L H O C ";
				String aa = "日期";
				String bb = "哪日能买入";
				Log.d(stock.getId(), aa, before.getDate(), bb, buyDate, cc, zdLow, zdHigh, zdOpen, zdClose);
			}
		}
		
	}

	/**
	 * 可以买入的那一天
	 */
	private static OneDayData getCanBuyDay(OneDayData before) {
		while (true) {
			double close = before.getClose();
			String zhangTingPrice = Market.toPriceString(close * 1.1);
			OneDayData tomorrow = before.getTomorrow();
			if (tomorrow == null)
				return null;
			double low = tomorrow.getLow();
			String tomorrowLow = Market.toPriceString(low);
			if (!zhangTingPrice.equals(tomorrowLow)) {
				return tomorrow;
			}
			before = tomorrow;
		}
	}
}
