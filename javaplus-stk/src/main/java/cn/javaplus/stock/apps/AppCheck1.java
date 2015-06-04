package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.moni.MacdLastGreenLine;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.stock.Stock2;

public class AppCheck1 {
	public static void main(String[] args) {
		StockMarket market = StockMarket.getInstance();
		List<Stock2> stocks = market.getStocks();
		MacdLastGreenLine l = new MacdLastGreenLine();
		for (Stock2 stc : stocks) {
			l.process(stc);
		}
		int winTimes = l.getWinTimes();
		int loseTimes = l.getLoseTimes();

		Log.d("winTimes", winTimes);
		Log.d("loseTimes", loseTimes);
		Log.d("getWinX", l.getWinX());
		Log.d("getLoseX", l.getLoseX());
		Log.d("getCount", l.getCount());
		Log.d("getProfitAll", l.getProfitAll());
		Log.d("getLoseXAvg", l.getLoseXAvg().getValue());
		Log.d("getWinXAvg", l.getWinXAvg().getValue());

	}
}
