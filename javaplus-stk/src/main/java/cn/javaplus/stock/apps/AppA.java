package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

/**
 * 上涨后,连跌N日, 明日涨幅均值
 */
public class AppA {

	public static void main(String[] args) throws Exception {

		StockReader r = new StockReader();

		List<Stock1> all = r.readAll();

		for (int n = 8; n < 12; n++) {
			analyze(n, all);
		}
	}

	private static void analyze(int n, List<Stock1> all) {

		Avg a = new Avg();

		for (Stock1 stock : all) {

			List<OneDayData> ps = stock.getDayDatas();
			for (OneDayData tomorrow : ps) {
				OneDayData today = tomorrow.getYestoday();
				if (today == null)
					continue;

				double up = today.getUp();
				if (up > 0 && isDown(stock, today, n)) { // 今日上涨了, 而且后N日下跌了
					OneDayData p = get(today, n + 1);
					if (p != null)
						a.add(p.getUp());
				}
			}

		}

		Log.d(n, "天", "涨幅均值", a.getValue(), a.getCount());
	}

	private static OneDayData get(OneDayData today, int n) {
		OneDayData p = today;
		for (int i = 0; i < n; i++) {
			p = p.getTomorrow();
			if (p == null)
				return null;
		}
		return p;
	}

	private static boolean isDown(Stock1 stock, OneDayData today, int n) {
		OneDayData p = today;

		for (int i = 0; i < n; i++) {
			p = p.getTomorrow();
			if (p == null)
				return false;

			if (p.getUp() > 0)
				return false;
		}
		Log.d(stock.getId(), "连续下跌", n, "天", "时间", today.getDate());
		return true;
	}

}
