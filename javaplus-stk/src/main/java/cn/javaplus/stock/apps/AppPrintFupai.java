package cn.javaplus.stock.apps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

/**
 * 打印近期复牌的股票
 */
public class AppPrintFupai {
	/**
	 * 最近多少天
	 */
	private static final int NEAR_DAY = 100;

	public static void main(String[] args) {
		StockMarket m = StockMarket.getInstance();
		List<Stock2> stocks = m.getStocks();

		for (Stock2 ss : stocks) {
			print(ss);
		}
	}

	private static void print(Stock2 ss) {
		List<DayData> dt = ss.getDayDatas();
		Collections.reverse(dt);
		dt = Util.Collection.sub(dt, NEAR_DAY);
		if(dt.get(0).getUp() < 0.095)
			return;
		for (DayData d : dt) {

			DayData yes = d.getYestoday();
			if (yes == null)
				break;

			if (getSpace(d, yes) > 30) {
				Log.d(d.getId(), d.getDate());
				break;
			}
		}
	}

	private static int getSpace(DayData d, DayData yes) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String date = d.getDate();
		String d1 = yes.getDate();
		try {
			Date dateI = sf.parse(date);
			Date d1I = sf.parse(d1);
			long a = (dateI.getTime() - d1I.getTime()) / Time.MILES_ONE_DAY;
			return (int) a;
		} catch (ParseException e) {
			return -1;
		}
	}
}
