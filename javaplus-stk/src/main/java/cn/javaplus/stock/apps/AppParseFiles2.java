package cn.javaplus.stock.apps;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.javaplus.log.Log;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 把分时文件转换得更详细, 只随机导入每日5个价格
 */
public class AppParseFiles2 {

	public class PlayerStock {
		String buyDate;
		String buyPrice;
		int count;
	}

	public class Player {
		double rmb = 3000000;

		public double getRmb() {
			return rmb;
		}

		List<PlayerStock> stocks = Lists.newArrayList();
		private double profit;

		public double getProfit() {
			return profit;
		}

		public int buy(String date, String price) {
			int canBuyCount = getCanBuyCount(price);
			if (canBuyCount > 0) {
				double p = new Double(price);
				rmb -= p * canBuyCount;
				addToPacket(date, price, canBuyCount);
			}
			return canBuyCount;
		}

		private int getCanBuyCount(String price) {
			double pr = new Double(price);
			return (int) (rmb / pr) * 100 / 100;
		}

		private void addToPacket(String date, String price, int canBuyCount) {
			PlayerStock p = new PlayerStock();
			p.buyDate = date;
			p.buyPrice = price;
			p.count = canBuyCount;
			stocks.add(p);
		}

		public String sellAll(String date, double priceNow) {
			Iterator<PlayerStock> it = stocks.iterator();
			double profit = 0;
			int countAll = 0;
			while (it.hasNext()) {
				AppParseFiles2.PlayerStock p = it.next();
				if (new Integer(date) > new Integer(p.buyDate)) {
					double buyPrice = new Double(p.buyPrice);
					double asset = priceNow * p.count;
					profit += (priceNow - buyPrice) * p.count;
					rmb += asset;
					it.remove();
				}
			}
			this.profit += profit;
			if(countAll > 0)
				
			return "S," + Market.toPriceString(priceNow) + "," + countAll + "," + Market.toPriceString(profit);
			else return ",,,";
		}
	}

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
		new AppParseFiles2().run();

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

		Player player = new Player();
		for (DayData data : datas) {
			print(data, sb, player);
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

	private void print(DayData data, StringBuilder sb, Player player) {

		List<PriceData> p = data.getPrices();

		Util.Collection.upset(p);
		p = Util.Collection.sub(p, 8);
		Collections.sort(p);

		for (PriceData pd : p) {
			String date = pd.getDate();
			String time = pd.getTime();
			String price = pd.getPrice();
			String closeYestoday = pd.getCloseYestoday();

			double close = new Double(closeYestoday);
			double priceNow = new Double(price);

			double up = (priceNow - close) / close;

			String upp = Market.toPriceString(up * 100) + "%";
			sb.append(date + "," + time + "," + price + "," + closeYestoday
					+ "," + upp + "," + pd.getHigh() + "," + pd.getLow());

			
			if (up < -0.029) {
				int count = player.buy(date, price);
				sb.append(",B," + price + "," + count);
			} else {
				sb.append(",,,");
			}

			String profit = player.sellAll(date, priceNow);
			sb.append(profit);
			sb.append("\r");
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
