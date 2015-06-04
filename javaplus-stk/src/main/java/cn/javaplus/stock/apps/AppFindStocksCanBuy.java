package cn.javaplus.stock.apps;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.quotes.IStockRecord;
import cn.javaplus.stock.quotes.StockRecord;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.stock.util.Market;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

/**
 * cong d:/stocks zhong xuanchu keyi goumai d gupiao
 * 
 * @author Administrator
 *
 */
public class AppFindStocksCanBuy {
	private static final class ComparatorImplementation implements
			Comparator<AppFindStocksCanBuy.StockOneDay> {
		@Override
		public int compare(StockOneDay o1, StockOneDay o2) {
			return o2.getX() - o1.getX();
		}
	}

	public static class StockOneDay {

		List<StockRecord> records = Lists.newArrayList();

		public StockOneDay(File file) {
			List<String> lines = Util.File.getLines(file);
			for (String line : lines) {
				if (!line.isEmpty()) {
					records.add(buildStockRecord(line));
				}
			}
		}

		private StockRecord buildStockRecord(String line) {
			String code = line.substring(0, 8);
			String fields = line.substring(9, line.length());
			StockRecord r = new StockRecord(code, fields);
			return r;
		}

		public String getCode() {
			return records.get(0).getCode();
		}

		public boolean canBuy() {
			Set<Object> set = Sets.newHashSet();
			for (IStockRecord r : records) {
				String now = r.getPriceNow();
				set.add(now);
			}
			return set.size() <= 2;
		}

		public long getChengJiaoLiangOn1456() {
			for (StockRecord s : records) {
				String c = s.getClock();
				if (c.contains("14:56")) {
					return new Long(s.getChengJiaoLiang());
				}
			}
			throw new RuntimeException();
		}

		public long getChengJiaoLiangOn0931() {
			for (StockRecord s : records) {
				String c = s.getClock();
				if (c.contains("09:31")) {
					return new Long(s.getChengJiaoLiang());
				}
			}
			return new Long(records.get(0).getChengJiaoLiang());
		}

		public long getBuy1On0931() {
			for (StockRecord s : records) {
				String c = s.getClock();
				if (c.contains("09:31") ) {
					return new Long(s.getBuyCount1());
				}
			}
			return new Long(records.get(0).getBuyCount1());
		}

		public long getBuy1On1456() {
			for (StockRecord s : records) {
				String c = s.getClock();
				if (c.contains("14:56")) {
					return new Long(s.getBuyCount1());
				}
			}
			throw new RuntimeException();
		}

		public String getName() {
			return records.get(0).getName();
		}

		public int getX() {
			Object a = getChengJiaoLiangOn1456();
			Object b = getBuy1On0931();
			int x = (int) (new Double(a + "") / new Double(b + "") * 100000);
			return x;
		}

		public long getTingPaiDays() {
			OneDayData yestoday = getxxx();
			if(yestoday == null)
				return -1;
			for (int i = 0; i < 15; i++) {
				OneDayData day = yestoday;
				yestoday = yestoday.getYestoday();
				long a = day.getDateMillis() - yestoday.getDateMillis();
				long b = a / Time.MILES_ONE_DAY;
				if (b > 8)
					return b;
			}
			long dateMillis = yestoday.getDateMillis();
			return (System.currentTimeMillis() - dateMillis)
					/ Time.MILES_ONE_DAY;
		}

		private OneDayData getxxx() {
			StockReader r = new StockReader();
			Stock1 read = r.read(getCode().replaceAll("[a-z]", ""));
			if(read == null)
				return null;
			OneDayData last = read.getLast();
			OneDayData yestoday = last.getYestoday();
			return yestoday;
		}

		public double getPrice() {
			double max = 0;
			for (IStockRecord s : records) {
				String now = s.getPriceNow();
				double a = new Double(now);
				if(a > max)
					max = a;
			}
			return max;
		}
		
		public String get11Price() {
			double p = getPrice();
			p = p * 1.1;
			return Market.toPriceString(p);
		}

	}

	public static void main(String[] args) {
		List<File> files = getFilesToday();

		List<StockOneDay> stocks = Lists.newArrayList();
		for (File file : files) {
			StockOneDay day = new StockOneDay(file);
//			if (day.canBuy()) {
				stocks.add(day);
//			}
		}

		Comparator<StockOneDay> c = new ComparatorImplementation();
		Collections.sort(stocks, c);

		for (StockOneDay day : stocks) {

			Object a = toPriceString(day.getChengJiaoLiangOn1456());

			Object b = toPriceString(day.getChengJiaoLiangOn0931());
			Object cc = toPriceString(day.getBuy1On0931());
			Object dd = toPriceString(day.getBuy1On1456());
			Object x = day.getX() + "/10W";
			d("09:31/14:56", day.getCode(), x, "buy1 " + cc + "/" + dd, "CJL "
					+ b + "/" + a, day.getName(), day.getTingPaiDays(), day.getPrice(), day.get11Price());
		}
	}

	private static Object toPriceString(long c) {
		return Market.toPriceString(c / 1000000d) + "W";
	}

	private static void d(Object... x) {
		for (int i = 0; i < x.length; i++) {
			x[i] = x[i] + "\t";
		}
		Log.d(x);
	}

	private static List<File> getFilesToday() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String s = f.format(new Date(System.currentTimeMillis()));
		File file = new File(Market.WORKSPACE_DIR);
		File[] fs = file.listFiles();
		ArrayList<File> ls = Lists.newArrayList();
		for (File ff : fs) {
			String name = ff.getName();
			if (name.contains(s))
				ls.add(ff);
		}
		return ls;
	}
}
