package cn.javaplus.stock.analyzers;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.time.Time;

/**
 * 跟2014年7月价格相比, 跌幅很大的
 */
public class G implements AnalyzeStrategy {

	private int dateNow;

	public G() {
		String d = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis() - Time.MILES_ONE_DAY));
		dateNow = new Integer(d);
		
//		dateNow = 20150705;
	}

	public boolean conform(Stock1 stock) {

		OneDayData last = stock.getLast();
		if (last == null)
			return false;

		if (!startsWith(stock.getId(), "60", "00", "30")) {
			return false;
		}

		boolean b = aaa(last);
		return b;
	}

	private boolean startsWith(String id, String... s) {
		for (String ss : s) {
			if (id.startsWith(ss))
				return true;
		}
		return false;
	}

	private boolean aaa(OneDayData last) {

		double close = last.getClose();
		double price = get201407(last);
		if (price == 0)
			return false;

		double d = (close - price) / price;
		
		boolean b = d < 0.0;
		if(b)
			Log.d(last.getId(), String.format("%.2f", d * 100) + "%");
		return b;
	}

	private double get201407(OneDayData last) {
//		Log.d(last.getDate());
		if (last.getDate() != dateNow)
			return 0;
		
		while (true) {
			last = last.getYestoday();
			if (last == null)
				return 0;

			int date = last.getDate();
			if (date < 20140701) {
				return last.getClose();
			}
		}
	}
}
