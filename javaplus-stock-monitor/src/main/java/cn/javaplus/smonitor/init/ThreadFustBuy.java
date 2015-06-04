package cn.javaplus.smonitor.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.smonitor.client.HengTaiClient;
import cn.javaplus.smonitor.downloader.Market;
import cn.javaplus.smonitor.downloader.Marks;
import cn.javaplus.smonitor.downloader.MarksAll;
import cn.javaplus.smonitor.downloader.SMonitor;
import cn.javaplus.smonitor.downloader.StockRecord;
import cn.javaplus.smonitor.downloader.StockRequester;
import cn.javaplus.util.Util;

public class ThreadFustBuy extends Thread {
	
	public class StockWithMarks {

		private final String id;
		private final Marks marks;

		public StockWithMarks(String id, Marks marks) {
			this.id = id;
			this.marks = marks;
		}

		public String getId() {
			return id;
		}

		public Marks getMarks() {
			return marks;
		}

	}

	
	private static final int BOUGHT_MARK = 11;
	private static final int FAST_BUY_MARK = 8;
	private static final int BU_DENG_SHANG_ZHANG_MARK = 12;
	
	private static ThreadFustBuy instance;
	boolean isStart;

	public void run() {
		Util.Thread.sleep(3000);
		int i = 0;
		while (true) {
			if (Market.inJiaoYiTime()) {
				try {
					fastBuy(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Util.Thread.sleep(6000);
				i ++;
				if(i % 60 == 0) {
					try {
						new HengTaiClient().refresh();
						Log.d("refresh heng tai client");
						Util.Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
			} else {
				Util.Thread.sleep(6000);
			}
			try {
				MarksAll all = SMonitor.getInstance().getMarksAll();
				all.save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void fastBuy(boolean isTest) {
		List<StockWithMarks> codesFustBuy = getCodesFustBuy();
		if (codesFustBuy.isEmpty())
			return;

		for (StockWithMarks s : codesFustBuy) {
			Log.d("codes fust buy", s.getId(), Market.getName(s.getId()));
		}
		
		StockRequester r = new StockRequester();
		List<StockRecord> rs = r.request(getIds(codesFustBuy));
		for (StockRecord ss : rs) {
			
			Log.d("check po ban", ss.getClock(), ss.getBuyCount1(), ss.getPriceNow(), ss.getCloseYestoday());
			
			boolean poBan = isPoBan(ss);
			if(poBan == true) {
				Log.d("[po ban:" , ss.getCode());
			}
			Marks marks = getMarks(codesFustBuy, ss);
			if ((poBan && myCondition(ss,marks))|| isTest) { // 破板 的情况下
				buy(ss, marks);
				break; // 只买1个, 不然如果同时出现2个, 有可能出现异常情况
			}
			lastPrices.put(ss.getCode(), new Double(ss.getPriceNow()));
		}
	}

	private Map<String, Double> lastPrices = Maps.newHashMap();
	
	private boolean myCondition(StockRecord ss, Marks marks) {
		
		if(marks.isMark(BU_DENG_SHANG_ZHANG_MARK)) {
			return true;
		}
		
		Double last = getLastPrice(ss);
		
		String n = ss.getPriceNow();
		double now = new Double(n);
		return now > last; //比上次请求价格高
	}

	private Double getLastPrice(StockRecord ss) {
		String id = ss.getCode();
		if(lastPrices.get(id) == null) {
			lastPrices.put(id, new Double(ss.getPriceNow()));
		}
		return lastPrices.get(id);
	}

	private Marks getMarks(List<StockWithMarks> codesFustBuy, StockRecord ss) {
		for (StockWithMarks s : codesFustBuy) {
			String id = Market.getCode(s.getId());
			String code = Market.getCode(ss.getCode());
			if (id.equals(code)) {
				return s.getMarks();
			}
		}
		return null;
	}

	private Collection<String> getIds(List<StockWithMarks> codesFustBuy) {
		ArrayList<String> ls = Lists.newArrayList();
		for (StockWithMarks stockWithMarks : codesFustBuy) {
			ls.add(stockWithMarks.getId());
		}
		return ls;
	}

	private List<StockWithMarks> getCodesFustBuy() {
		SMonitor ins = SMonitor.getInstance();
		MarksAll marksAll = ins.getMarksAll();

		Collection<String> ids = ins.getStockIds();

		ArrayList<StockWithMarks> ls = Lists.newArrayList();
		for (String id : ids) {
			Marks marks = marksAll.get(id);

			boolean hasBought = marks.isMark(BOUGHT_MARK);
			if (marks.isMark(FAST_BUY_MARK) && !hasBought) {
				ls.add(new StockWithMarks(id, marks));
			}
		}
		return ls;
	}

	private boolean isPoBan(StockRecord ss) {

		String yestoday = ss.getCloseYestoday();
		Double yes = new Double(yestoday);

		String now = ss.getPriceNow();
		Double n = new Double(now);

		String maxBuyPrice = Market.toPriceString(yes * 1.1);
		String minBuyPrice = Market.toPriceString(yes * 1.06);

		return new Double(minBuyPrice) < n + 0.01 && n + 0.01 < new Double(maxBuyPrice);
	}

	private void buy(StockRecord ss, Marks marks) {
		if(boughts.contains(ss.getCode())) {
			return;
		}
		robotBuy(ss, marks);
		markBuy(ss);
	}

	private void robotBuy(StockRecord ss, Marks marks) {
		int count = getCount(marks);
		String code = ss.getCode();
		
		String buyPrice = getBuyPrice(ss);
		
		Log.d("buy", code, Market.getName(code), buyPrice, count);
		new HengTaiClient().buy(code, buyPrice, count);
		Log.d("buy success", code, Market.getName(code), buyPrice, count);
	}

	private String getBuyPrice(StockRecord ss) {
		String n = ss.getPriceNow();
		String cy = ss.getCloseYestoday();
		double close = new Double(cy);
		double maxBuyPrice = close * 1.1;
		double now = new Double(n);
		double buyPrice = now + 0.2; // 高2毛钱, 保证买到
		if(buyPrice > maxBuyPrice) {
			buyPrice = maxBuyPrice;
		}
		
		return Market.toPriceString(buyPrice);
	}

	private int getCount(Marks marks) {
		if (marks.isMark(10))
			return 300;
		if (marks.isMark(9))
			return 200;
		return 100;
	}
	private Set<String> boughts = Sets.newHashSet();
	private void markBuy(StockRecord ss) {
		SMonitor.getInstance().mark(ss.getCode(), BOUGHT_MARK);
		SMonitor.getInstance().mark(ss.getCode(), BOUGHT_MARK - 3);
		
		boughts.add(ss.getCode());
	}

	@Override
	public synchronized void start() {
		if (!isStart) {
			super.start();
			isStart = true;
		}
	}

	public void test(String id ) {
		fastBuy(true);
//		new HengTaiClient().buy("000010", "1.01", 100);
//		new HengTaiClient().refresh();
//		new HengTaiClient().buy("sz002755", "17.39", 200);
//		new HengTaiClient().refresh();
	}
	
	public static ThreadFustBuy getInstance() {
		if (instance == null)
			instance = new ThreadFustBuy();
		return instance;
	}
}
