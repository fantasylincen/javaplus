package cn.javaplus.stock.apps;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.javaplus.log.Log;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 把分时文件转换得更详细
 */
public class AppParseFiles {

	public class PriceData implements Comparable<PriceData> {

		String Date;

		String Time;

		String Price;

		String CloseYestoday;

		private String high;

		private String low;

		public String getDate() {
			return Date;
		}

		public void setDate(String date) {
			Date = date;
		}

		public String getTime() {
			return Time;
		}

		public void setTime(String time) {
			Time = time;
		}

		public String getPrice() {
			return Price;
		}

		public void setPrice(String price) {
			Price = price;
		}

		public String getCloseYestoday() {
			return CloseYestoday;
		}

		public void setCloseYestoday(String closeYestoday) {
			CloseYestoday = closeYestoday;
		}

		@Override
		public int compareTo(PriceData o) {
			int p = getDate().compareTo(o.getDate());
			if (p != 0)
				return p;
			return getTime().compareTo(o.getTime());
		}

		public String getLow() {
			return low;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public String getHigh() {
			return high;
		}

		public void setHigh(String high) {
			this.high = high;
		}

	}

	public class DayData implements Comparable<DayData> {

		List<PriceData> prices;
		private String date;

		public String getClosePrice() {
			PriceData priceData = prices.get(prices.size() - 1);
			return priceData.getPrice();
		}

		public List<PriceData> getPrices() {
			return prices;
		}

		public void setPrices(List<PriceData> prices) {
			this.prices = prices;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public void add(String date, String time, String price) {
			PriceData p = new PriceData();
			p.setDate(date);
			p.setPrice(price);
			p.setTime(time);
			prices.add(p);
		}

		public void setCloseYestodayPrice(String closePrice) {
			for (PriceData d : prices) {
				d.setCloseYestoday(closePrice);
			}
		}

		public String getOpenPrice() {
			PriceData priceData = prices.get(0);
			return priceData.getPrice();
		}

		public String getHighPrice() {
			double max = Double.MIN_VALUE;

			for (PriceData p : prices) {
				String pp = p.getPrice();
				Double price = new Double(pp);
				if (price > max)
					max = price;
			}
			return Market.toPriceString(max);
		}

		public String getLowPrice() {
			double min = Double.MAX_VALUE;

			for (PriceData p : prices) {
				String pp = p.getPrice();
				Double price = new Double(pp);
				if (price < min)
					min = price;
			}
			return Market.toPriceString(min);
		}

		@Override
		public int compareTo(DayData o) {
			int p = getDate().compareTo(o.getDate());
			return p;
		}

	}

	private static final String SRC = "G:/来自标普永华/5min2008/";
	private static final String DST = "G:/来自标普永华/5min2008Parse/";

	public static void main(String[] args) {
		new AppParseFiles().run();

	}

	private void run() {
		ensureDstExists();
		int a = 0;
		File file = new File(SRC);

		File[] all = file.listFiles();
		for (File f : all) {
			parse(f);
			Log.d(f.getName(), a++);
		}
	}

	private void ensureDstExists() {
		File dst = new File(DST);
		if (!dst.exists()) {
			dst.mkdir();
		}
	}

	private void parse(File f) {

		String name = f.getName();

		if (name.endsWith(".csv")) {
			List<String> lines = Util.File.getLines(f);
			saveToNewFile(lines, DST + name);
		}
	}

	private void saveToNewFile(List<String> lines, String filePath) {

		List<DayData> datas = buildDayDatas(lines);

		for (int i = 0; i < datas.size(); i++) {
			DayData data = datas.get(i);
			DayData yestoday = getYestoday(datas, i);
			if (yestoday != null) {
				data.setCloseYestodayPrice(yestoday.getClosePrice());
			} else {
				data.setCloseYestodayPrice(data.getOpenPrice());
			}
		}

		StringBuilder sb = new StringBuilder();
		for (DayData data : datas) {
			print(data, sb);
		}

		Util.File.write(filePath, sb.toString());
	}

	private List<DayData> buildDayDatas(List<String> lines) {
		Map<String, DayData> datas = Maps.newHashMap();
		for (String line : lines) {
			put(datas, line);
		}
		ArrayList<DayData> ls = Lists.newArrayList(datas.values());
		Collections.sort(ls);

		for (DayData data : ls) {
			List<PriceData> ps = data.getPrices();

			String high = getHighPrice(ps);
			String low = getLowPrice(ps);

			setHigh(ps, high);
			setLow(ps, low);

			Collections.sort(ps);
		}
		return ls;
	}

	private String getLowPrice(List<PriceData> ps) {
		double min = Double.MAX_VALUE;
		for (PriceData dt : ps) {
			String p = dt.getPrice();
			double price = new Double(p);
			if (price < min)
				min = price;

		}
		return Market.toPriceString(min);
	}

	private String getHighPrice(List<PriceData> ps) {
		double max = Double.MIN_VALUE;
		for (PriceData dt : ps) {
			String p = dt.getPrice();
			double price = new Double(p);
			if (price > max)
				max = price;

		}
		return Market.toPriceString(max);
	}

	private void setLow(List<PriceData> ps, String low) {
		for (PriceData d : ps) {
			d.setLow(low);
		}
	}

	private void setHigh(List<PriceData> ps, String high) {
		for (PriceData d : ps) {
			d.setHigh(high);
		}
	}

	private void put(Map<String, DayData> datas, String line) {

		String[] ss = line.split(",");
		String date = parse(ss[0]);
		String time = parseTime(ss[1]);
		String price = ss[2];

		DayData dd = datas.get(date);

		if (dd == null) {
			dd = new DayData();
			List<PriceData> prices = Lists.newArrayList();
			dd.setPrices(prices);
			dd.setDate(date);

			datas.put(date, dd);
		}

		dd.add(date, time, price);
	}

	private void print(DayData data, StringBuilder sb) {

		List<PriceData> p = data.getPrices();
		for (PriceData pd : p) {
			String date = pd.getDate();
			String time = pd.getTime();
			String price = pd.getPrice();
			String closeYestoday = pd.getCloseYestoday();

			if(time.length() <= 3) {
				time = "0" + time;
			}
			
			sb.append(date + "," + time + "," + price + "," + closeYestoday
					+ "," + pd.getHigh() + "," + pd.getLow() + "\r");
		}
	}

	private DayData getYestoday(List<DayData> datas, int i) {
		int index = i - 1;
		if (index < 0)
			return null;
		return datas.get(index);
	}

	private String parseTime(String time) {
		String[] split = time.split(":");
		int hour = new Integer(split[0]);
		int min = new Integer(split[1]);
		return two(hour) + ":" + two(min);
	}

	private String two(int v) {
		if (v < 10)
			return "0" + v;
		return v + "";
	}

	private String parse(String date) {
		String[] ss = date.split("/");
		String year = ss[0];
		int m = new Integer(ss[1]);
		int d = new Integer(ss[2]);
		return year + two(m) + two(d);
	}

}
