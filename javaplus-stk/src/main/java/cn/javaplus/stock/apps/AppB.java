package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

import com.google.common.collect.Lists;

/**
 * 某日以某个涨幅购买后, 明日涨幅均值
 */
public class AppB {

	public static class Scope {
		private double min;
		private double max;

		public Scope(double min, double max) {
			this.min = min;
			this.max = max;
		}

		public double getMin() {
			return min;
		}

		public void setMin(double min) {
			this.min = min;
		}

		public double getMax() {
			return max;
		}

		public void setMax(double max) {
			this.max = max;
		}

	}

	public static void main(String[] args)
			 
			throws Exception {

		StockReader r = new StockReader();

		List<Stock1> all = r.readAll();

		List<Scope> sps = Lists.newArrayList();
		append(sps, -0.11, -0.1);
		append(sps, -0.096, -0.09);
		append(sps, -0.09, -0.08);
		append(sps, -0.08, -0.07);
		append(sps, -0.07, -0.06);
		append(sps, -0.06, -0.05);
		append(sps, -0.05, -0.04);
		append(sps, -0.04, -0.03);
		append(sps, -0.03, -0.02);
		append(sps, -0.02, -0.01);

		append(sps, -0.01, -0.005);
		append(sps, -0.005, 0.005);
		append(sps, 0.01, 0.02);
		append(sps, 0.02, 0.03);
		append(sps, 0.03, 0.04);
		append(sps, 0.04, 0.05);
		append(sps, 0.05, 0.06);
		append(sps, 0.06, 0.07);
		append(sps, 0.07, 0.08);
		append(sps, 0.08, 0.09);
		append(sps, 0.09, 0.095);
		append(sps, 0.10, 0.11);

		for (Scope scope : sps) {
			analyze(scope, all);
		}

	}

	private static void append(List<Scope> sps, double min, double max) {
		if (min >= max)
			throw new IllegalArgumentException();
		sps.add(new Scope(min, max));
	}

	private static void analyze(Scope scope, List<Stock1> allStocks) {

		double min = scope.getMin();
		double max = scope.getMax();

		double all = 0;
		int count = 0;

		for (Stock1 stock : allStocks) {

			List<OneDayData> ps = stock.getDayDatas();
			for (OneDayData tomorrow : ps) {
				OneDayData today = tomorrow.getYestoday();
				if (today == null)
					continue;

				double up = today.getUp();
				if (up > min && up < max) {
					all += tomorrow.getUp();
					count++;
				}
			}

		}
		Log.d("今日涨幅", min, max, "明日涨幅",
				String.format("%.2f", all / count * 100) + "%");

	}

}
