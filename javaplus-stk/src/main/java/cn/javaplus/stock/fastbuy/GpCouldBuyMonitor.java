package cn.javaplus.stock.fastbuy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;

import com.google.common.collect.Maps;

public class GpCouldBuyMonitor extends Thread {

	static PrintStream out = createPrintStream();

	// 购买时间段
	public static int MIN = 91450;
	public static int MAX = 91820;
	public static final int REFRESH_BUTTON_X = 459;
	public static final int REFRESH_BUTTON_Y = 327;

	public static final int BUY_TEXT_FIELD_X = 362;
	public static final int BUY_TEXT_FIELD_Y = 123;

	public static final int OK_BUTTON_X = 414;
	public static final int OK_BUTTON_Y = 430;
//	boolean IS_DEBUG = true;

	 boolean IS_DEBUG = false;

	public class Gp {

		String name;
		String code;
		String priceClose;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getPriceClose() {
			return priceClose;
		}

		public void setPriceClose(String priceClose) {
			this.priceClose = priceClose;
		}
	}

	private Collection<Gp> gpCouldBuy = Sets.newConcurrentHashSet();
	private Map<String, String> prices = Maps.newHashMap();// 停牌前价格

	public GpCouldBuyMonitor() {
		out.println("GpCouldBuyMonitor new");
		loadPriceBeforeTingPai();

		if (IS_DEBUG) {

			Date date = new Date(System.currentTimeMillis() + 3000);
			String time = sf.format(date);

			MIN = new Integer(time);// 购买时间段
			MAX = MIN + 20;// 购买时间段

			prices.put("sz002240", "6.34");
		}
	}

	private void loadPriceBeforeTingPai() {
		List<Stock1> all = new StockReader().readShenHuA();
		for (Stock1 stock : all) {
			OneDayData last = stock.getLast();
			if (last == null)
				continue;

			long dateMillis = last.getDateMillis();

			boolean hasTingPai30Day = System.currentTimeMillis() - dateMillis > Time.MILES_ONE_DAY * 30;

			if (hasTingPai30Day) {
				String id = stock.getId();
				if (last.getDate() < 20140901) {
					continue;
				}
				if (id.startsWith("300")) {
					continue;
				}
				if (last.getClose() > 35)
					continue;

				id = parseId(id);
				String price = toPriceString(last.getClose());
				prices.put(id, price);
				Log.d("停牌30天以上的股票:" + id + "  价格:" + price + "    建议价格:"
						+ toPriceString(new Double(price) * 1.1));
			}
		}

		if (IS_DEBUG) {
			new RuntimeException("请注意是调试模式").printStackTrace();
		}
	}

	private String parseId(String id) {
		if (id.startsWith("60"))
			return "sh" + id;
		else
			return "sz" + id;
	}

	/**
	 * 保留2位小数 四舍五入
	 */
	private String toPriceString(double close) {
		return String.format("%.2f", close);
	}

	public Collection<Gp> getGpCouldBuy() {
		return gpCouldBuy;
	}

	SimpleDateFormat sf = new SimpleDateFormat("HHmmss");

	@Override
	public void run() {

		while (true) {
			Date date = new Date(System.currentTimeMillis());
			String time = sf.format(date);
			int now = new Integer(time);
			if (MIN < now && now < MAX) {
				try {

					fetchGps(now);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Util.Thread.sleep(50);
		}
	}

	private void fetchGps(int now) {

		long t1 = System.currentTimeMillis();

		String url = "http://hq.sinajs.cn/list="
				+ Util.Collection.linkWith(",", prices.keySet());
		long t2 = System.currentTimeMillis();
		Log.d("耗时", t2 - t1);
		String content = WebContentFethcer.get("gb2312", url);

		// Log.d(content);
		out.println(now);
		out.println(content);

		if (IS_DEBUG && now < MIN + 2) {
			content = content.replaceAll("var hq_str_sz002240.+;", "");
		}

		String[] lines = content.split(";");

		for (String line : lines) {
			line = line.trim();
			if (line.isEmpty()) {
				continue;
			}

			String code = line.split("=")[0].replace("var hq_str_", "");
			String ee = line.split("=")[1].replace("\"", "");

			if (ee.isEmpty()) {
				continue;
			}

			String[] e = ee.split(",");

			float buy1 = new Float(e[11]);
			float yestoday = new Float(e[2]);
			float bidding1 = new Float(e[6]);
			String name = e[0];

			if (Math.abs(buy1 - 0) < 0.001 && Math.abs(bidding1 - 0) < 0.001) {
				continue;
			}

			Log.d("发现复牌的股票", name, buy1, yestoday, bidding1);

			String closePrice = prices.remove(code);
			if (closePrice == null) {
				Log.d("竟然没有价格?? " + code);
				continue;
			}

			Gp gp = new Gp();
			gp.setPriceClose(closePrice);
			gp.setCode(code);
			gp.setName(name);

			gpCouldBuy.add(gp);
		}

	}

	private static PrintStream createPrintStream() {

		try {
			return new PrintStream(new File("out.txt"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
