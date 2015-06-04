package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.stock.util.Market;

/**
 * 60 ri zui di jia mai ru
 */
public class AppPrintBuyStratege2 {

	static class Bean {

		/**
		 * N ri zui di jia
		 */
		int day;

		/**
		 * chi you tian shu
		 */
		int next;

		/**
		 * DAY ri zui gao jia de PERCENT
		 */
		double percent;
		/**
		 * 成交量比例
		 */
		double chengJiaoLiangPercent;
		/**
		 * 成交量统计天数
		 */
		double chengJiaoLiangDay;

		public double getChengJiaoLiangPercent() {
			return chengJiaoLiangPercent;
		}

		public void setChengJiaoLiangPercent(double chengJiaoLiangPercent) {
			this.chengJiaoLiangPercent = chengJiaoLiangPercent;
		}

		public double getChengJiaoLiangDay() {
			return chengJiaoLiangDay;
		}

		public void setChengJiaoLiangDay(double chengJiaoLiangDay) {
			this.chengJiaoLiangDay = chengJiaoLiangDay;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getNext() {
			return next;
		}

		public void setNext(int next) {
			this.next = next;
		}

		public double getPercent() {
			return percent;
		}

		public void setPercent(double percent) {
			this.percent = percent;
		}

		Avg avg = new Avg();
		Avg avgNianHua = new Avg();

		private double print(Bean bean) {
			/*
			 * // for (int i = 1996; i < 2016; i++) { // String year = i + "";
			 * // print(year, bean); // }
			 */

			printAllYear(bean);

			double k = avgNianHua.getValue() - 1;
			double x = 200d / bean.getNext();
			double nianHua = Math.pow(k + 1, x) - 1;

			d("zong nian hua", nianHua * 100 + "%", "bean", bean,
					"tongjiliang", avgNianHua.getCount());
			return nianHua;
		}

		private void d(Object... obj) {
			StringBuilder sb = new StringBuilder();
			for (Object object : obj) {
				sb.append("," + object);
			}
			System.out.println(sb.toString()/*.replaceFirst(",", "")*/);
		}

		private void printAllYear(final Bean bean) {

			It it = new It() {

				@Override
				public void onRead(Stock1 stock) {
					analyze(stock.getId(), stock.getDayDatas(), bean);
				}
			};

			new StockReader().foreachShenHu(it);

			if (avg.getCount() == 0) {
				return;
			}
			/*
			 * Log.d(year, "年"); Log.d("只要在" + DAY + "日内最高价的" + PERCENT * 100 +
			 * "%, 就买入"); Log.d("买入" + NEXT + "天强制平仓"); Log.d("卖价与买价平均比值",
			 * avg.getValue()); Log.d("统计量" + avg.getCount()); double k =
			 * avg.getValue() - 1; Log.d(NEXT + "日盈利", k * 100 + "%");
			 * Log.d("年化收益率", (k * 200 / NEXT) * 100 + "%"); Log.d("");
			 * Log.d(""); Log.d("");
			 */
		}

		/*
		 * private void print(final String year, final Bean bean) {
		 * 
		 * It it = new It() {
		 * 
		 * @Override public void onRead(Stock stock) { stock.fuQuan();
		 * analyze(stock.getId(), stock.getPrices(year), bean); } };
		 * 
		 * new StockReader().foreachShenHu(it);
		 * 
		 * if (avg.getCount() == 0) return;
		 * 
		 * // Log.d(year, "年");
		 * 
		 * // Log.d("只要在" + DAY + "日内最高价的" + PERCENT * 100 + "%, 就买入"); //
		 * Log.d("买入" + NEXT + "天强制平仓"); // Log.d("卖价与买价平均比值", avg.getValue());
		 * // Log.d("统计量" + avg.getCount()); double k = avg.getValue() - 1; //
		 * Log.d(NEXT + "日盈利", k * 100 + "%"); // Log.d("年化收益率", (k * 200 /
		 * NEXT) * 100 + "%");
		 * 
		 * // Log.d(""); // Log.d(""); // Log.d(""); }
		 */
		private void analyze(String id, List<OneDayData> prices, Bean bean) {
			for (int i = bean.getDay() + 10; i < prices.size(); i++) {

				OneDayData p = prices.get(i);

				if (isBelowHighest(p, bean) && isChengJiaoLiangRight(bean, p)
						&& hasNextDay(p, bean.getNext())) {
					double buyPrice = p.getAvg();
					OneDayData sell = getNextPriceAvg(p, bean.getNext());
					double sellPrice = sell.getAvg();

					String sellPri = Market.toPriceString(sellPrice);
					String buyPri = Market.toPriceString(buyPrice);
					String s = sellPrice > buyPrice ? "O" : "X";

					d("code", id, "是否盈利", s, "买入日期", p.getDate(), "买价", buyPri,
							"卖出日期", sell.getDate(), "卖价", sellPri, "盈利率",
							(sellPrice - buyPrice) / buyPrice);

					double up = sellPrice / buyPrice;
					avg.add(up);
					avgNianHua.add(up);

					// i += bean.getNext() - 1;

				}
			}
		}

		private boolean isChengJiaoLiangRight(Bean bean, OneDayData p) {
			long v = p.getVolume();
			double day = bean.getChengJiaoLiangDay();
			double per = bean.getChengJiaoLiangPercent();

			double avg = p.getAvgChengJiaoLiang(day);
			return v / avg <= per;
		}

		private boolean isBelowHighest(OneDayData p, Bean bean) {
			int day2 = bean.getDay();
			OneDayData h = p.getHighestPrice(day2);

			// Log.d("max date", h.getDate(), "max price", h.getAvg(),
			// "now date", p.getDate(), p.getId());
			double price = h.getAvg();
			double d = p.getAvg() / price;
			double pc = bean.getPercent();
			boolean b = d < pc;
			return b;
		}

		/**
		 * shi fou you nextDay de shuju
		 */
		private boolean hasNextDay(OneDayData p, int nextDay) {
			for (int i = 0; i < nextDay; i++) {
				p = p.getTomorrow();
				if (p == null) {
					return false;
				}
			}
			return true;
		}

		private OneDayData getNextPriceAvg(OneDayData p, int nextDay) {

			for (int i = 0; i < nextDay; i++) {
				p = p.getTomorrow();
			}
			return p;
		}

		@Override
		public String toString() {
			return "" + day + ", " + next + ", " + percent + ", "
					+ chengJiaoLiangDay + ", " + chengJiaoLiangPercent + "";
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		List<Bean> ls = Lists.newArrayList();

		int day = 33;// 今天的价格A, 33日最高价B, 今天前8日平均成交量C, 昨日成交量D, 当 A<=B*0.8 且
						// D=C*0.5 就立即买入, 29天强制卖出
		int next = 29;
		double percent = 0.8;
		double chengJiaoLiangPercent = 0.5; // 成交量比例
		double chengJiaoLiangDay = 8; // 成交量统计天数

		for (int i = 0; i < 1; i++) {
			add(ls, day, next, percent, chengJiaoLiangPercent,
					chengJiaoLiangDay);
			// next++;
			// day++;
			// percent -= 0.05;
			// chengJiaoLiangPercent += 0.05;
			// chengJiaoLiangDay ++;
		}

		System.setOut(new PrintStream("xx.csv"));

		double max = 0;
		Bean maxBean = null;

		for (Bean bean : ls) {
			double p = bean.print(bean);
			if (p > max) {
				max = p;
				maxBean = bean;
			}
		}
		Log.d("最高年化收益", max * 100 + "%", maxBean);
	}

	private static void add(List<Bean> ls, int day, int next, double percent,
			double chengJiaoLiangPercent, double chengJiaoLiangDay) {
		Bean b = new Bean();
		b.setDay(day);
		b.setNext(next);
		b.setPercent(percent);
		b.setChengJiaoLiangDay(chengJiaoLiangDay);
		b.setChengJiaoLiangPercent(chengJiaoLiangPercent);
		ls.add(b);
	}
}
