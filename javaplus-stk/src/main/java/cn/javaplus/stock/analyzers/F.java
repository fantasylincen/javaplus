package cn.javaplus.stock.analyzers;

import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.time.Time;

public class F implements AnalyzeStrategy {

	private static final int DAY = 102;
	static int day = 5; // 统计天数

	public F() {
		Log.d("停 " + DAY + "日以上,  " + day + "日内 复牌");
	}

	public boolean conform(Stock1 stock) {

		OneDayData last = stock.getLast();
		if (last == null)
			return false;

		if (!startsWith(stock.getId(), "60", "30", "00", "20", "90")) {
			return false;
		}

		boolean b = nTianNeiYouYiGeJianGe(day, DAY, last);
		// if(b)
		// Log.d(stock.getId());
		return b;
	}

	private boolean startsWith(String id, String... s) {
		for (String ss : s) {
			if (id.startsWith(ss))
				return true;
		}
		return false;
	}

	/**
	 * N天内有一个间隔
	 * 
	 * @param n
	 * @param daySpace
	 *            间隔时长(天)
	 * @param last
	 * @return
	 */
	private boolean nTianNeiYouYiGeJianGe(int n, int daySpace, OneDayData last) {

		for (int i = 0; i < n; i++) {

			long time1 = last.getDateMillis();
			last = last.getYestoday();
			if (last == null)
				return false;

			long time2 = last.getDateMillis();

			if (time1 - time2 > Time.MILES_ONE_DAY * daySpace) {
				return true;
			}
		}
		return false;
	}
}
