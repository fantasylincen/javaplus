package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.MoniPlayerDao;
import cn.javaplus.stock.gen.dto.MongoGen.MoniPlayerDto;
import cn.javaplus.stock.gen.dto.MongoGen.MyStockDto;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDto;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.quotes.IStockRecord;
import cn.javaplus.stock.quotes.StockRecord;
import cn.javaplus.stock.quotes.StockRequester;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.time.taskutil.TaskCenter;
import cn.javaplus.time.taskutil.TaskSafety;
import cn.javaplus.util.Util;

public class AppMoniBuy1 {

	// public static class P implements IStockRecord {
	// String code;
	// String date;
	// String name;
	// String closeYestoday;
	// String priceNow;
	// String highToday;
	// String lowToday;
	//
	// public String getCode() {
	// return code;
	// }
	//
	// public void setCode(String code) {
	// this.code = code;
	// }
	//
	// public String getDate() {
	// return date;
	// }
	//
	// public void setDate(String date) {
	// this.date = date;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getCloseYestoday() {
	// return closeYestoday;
	// }
	//
	// public void setCloseYestoday(String closeYestoday) {
	// this.closeYestoday = closeYestoday;
	// }
	//
	// public String getPriceNow() {
	// return priceNow;
	// }
	//
	// public void setPriceNow(String priceNow) {
	// this.priceNow = priceNow;
	// }
	//
	// public String getHighToday() {
	// return highToday;
	// }
	//
	// public void setHighToday(String highToday) {
	// this.highToday = highToday;
	// }
	//
	// public String getLowToday() {
	// return lowToday;
	// }
	//
	// public void setLowToday(String lowToday) {
	// this.lowToday = lowToday;
	// }
	//
	// /**
	// * 涨停?
	// */
	// public boolean isZhangTing() {
	// return getUp() > 0.095;
	// }
	//
	// /**
	// * 跌停?
	// */
	// public boolean isDieTing() {
	// return getUp() < -0.095;
	// }
	//
	// @Override
	// public double getUp() {
	// String c = getCloseYestoday();
	// double close = new Double(c);
	// double now = new Double(getPriceNow());
	// return (now - close) / close;
	// }
	//
	// }

	public static class StockRecordAdpt implements IStockRecord {

		private StockPriceDto dto;

		public StockRecordAdpt(StockPriceDto dto) {
			this.dto = dto;
		}

		@Override
		public String getCode() {
			return dto.getId();
		}

		@Override
		public String getDate() {
			return dto.getTime().split(":")[0];
		}

		@Override
		public String getName() {
			return "XXX";
		}

		@Override
		public String getCloseYestoday() {
			return dto.getCloseYestoday();
		}

		@Override
		public String getPriceNow() {
			return dto.getPrice();
		}

		@Override
		public String getHighToday() {
			return dto.getHighToday();
		}

		@Override
		public String getLowToday() {
			return dto.getLowToday();
		}

		/**
		 * 涨停?
		 */
		public boolean isZhangTing() {
			return getUp() > 0.095;
		}

		/**
		 * 跌停?
		 */
		public boolean isDieTing() {
			return getUp() < -0.095;
		}

		@Override
		public double getUp() {
			String c = getCloseYestoday();
			double close = new Double(c);
			double now = new Double(getPriceNow());
			return (now - close) / close;
		}

	}

	public static class CsvRequester implements MoniStockRequester {

		// private static Map<String, P> CSV_STOCK_DATA;
		//
		// public static Map<String, P> getCsvStockData() {
		// if (CSV_STOCK_DATA == null) {
		// CSV_STOCK_DATA = loadAllStocks();
		// }
		// return CSV_STOCK_DATA;
		// }
		//
		// private static Map<String, P> loadAllStocks() {
		// HashMap<String, P> newHashMap = Maps.newHashMap();
		// Set<String> all = Market.getSelectStocks();
		// for (String code : all) {
		// String fileName = "G:/来自标普永华/5min2008Parse/"
		// + Market.getCode(code).toUpperCase() + ".csv";
		// List<String> lines = Util.File.getLines(fileName);
		// for (String line : lines) {
		// String[] ss = line.split(",");
		//
		// String date = ss[0];
		// String clock = ss[1];
		// String price = ss[2];
		// String closeYes = ss[3];
		// String high = ss[4];
		// String low = ss[5];
		//
		// P p = new P();
		// p.setPriceNow(price);
		// p.setCloseYestoday(closeYes);
		// p.setName("UNDEFINE");
		// p.setDate(date);
		// p.setCode(code);
		// p.setHighToday(high);
		// p.setLowToday(low);
		//
		// newHashMap.put(code + ":" + date + ":" + clock, p);
		// }
		// Log.d("load over", code);
		// }
		// return newHashMap;
		// }

		private MoniTimer timer;

		public CsvRequester(MoniTimer timer) {
			this.timer = timer;
		}

		@Override
		public List<IStockRecord> request(Set<String> ids) {
			ArrayList<IStockRecord> ls = Lists.newArrayList();
			for (String id : ids) {
				IStockRecord request = request(id);
				if (request != null)
					ls.add(request);
			}
			return ls;
		}

		private IStockRecord request(String id) {
			// Map<String, P> map = getCsvStockData();
			//
			// P price = map.get(id + ":" + timer.getDateNow() + ":"
			// + timer.getClockNow());
			// return price;

			StockPriceDao dao = Daos.getStockPriceDao();
			String time = timer.getDateNow() + ":" + timer.getClockNow();
			StockPriceDto dto = dao.get(id, time);
			if (dto == null) {
				return null;
			}
			return new StockRecordAdpt(dto);
		}

	}

	public static class CsvDataLooper implements EveryDayLooper {

		public class DayMoni extends Thread {

			@Override
			public void run() {
				for (int i = 0; i < JIAO_YI_DAY; i++) {
					processTasks();
					nextDay();
				}
			}

			private void processTasks() {
				List<EveryDayTask> as = Lists.newArrayList(tasks);
				Collections.sort(as);
				for (EveryDayTask t : as) {
					try {
						clockNow = t.getTime();
						t.getTask().runSafty();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		public class EveryDayTask implements Comparable<EveryDayTask> {

			private String time;
			private TaskSafety task;

			public EveryDayTask(String time, TaskSafety task) {
				this.time = time;
				this.task = task;
			}

			public TaskSafety getTask() {
				return task;
			}

			public String getTime() {
				return time;
			}

			@Override
			public int compareTo(EveryDayTask o) {
				return getTime().compareTo(o.getTime());
			}
		}

		private DayMoni dayMoni;
		private Set<EveryDayTask> tasks = Sets.newConcurrentHashSet();
		private String clockNow;
		private int date;

		public CsvDataLooper() {
			setDate(DATE_START);
		}

		@Override
		public void loopEveryDay(String time, TaskSafety task) {
			addTask(time, task);
			ensureThreadStart();
		}

		private void addTask(String time, TaskSafety task) {
			tasks.add(new EveryDayTask(time, task));
		}

		private void ensureThreadStart() {

			if (dayMoni == null) {
				dayMoni = new DayMoni();
				dayMoni.start();
			}
		}

		@Override
		public String getClockNow() {
			return clockNow;
		}

		@Override
		public int getDateNow() {
			return date;
		}

		public void setDate(int date) {
			this.date = date;
		}

		public int getDate() {
			return date;
		}

		/**
		 * 下个交易日
		 */
		public void nextDay() {
			date = Market.nextDate(date);
			if (isSaturday()) {
				date = Market.nextDate(date);
				date = Market.nextDate(date);
			}
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		/**
		 * 是否是星期六
		 */
		private boolean isSaturday() {
			Date dt;
			try {
				dt = sf.parse(date + "");
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			int dd = c.get(Calendar.DAY_OF_WEEK);
			return dd == Calendar.SATURDAY;
		}

	}

	public class SinaStock {

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public double getPriceNow() {
			return priceNow;
		}

		public void setPriceNow(double priceNow) {
			this.priceNow = priceNow;
		}

		public double getUp() {
			return up;
		}

		public void setUp(double up) {
			this.up = up;
		}

		String code;
		double priceNow;
		double up;
	}

	public static class StockRecordAdaptor implements IStockRecord {

		private DayData today;
		private Stock2 stock;
		private String priceNow;

		public StockRecordAdaptor(Stock2 stock) {
			this.stock = stock;
			today = StockMarket.getInstance().getToday(stock);
		}

		@Override
		public String getCode() {
			return stock.getId();
		}

		@Override
		public String getDate() {
			if (today == null)
				return null;
			return today.getDate();
		}

		@Override
		public String getName() {
			return stock.getName();
		}

		@Override
		public String getCloseYestoday() {
			if (today == null)
				return null;
			DayData yestoday = today.getYestoday();
			if (yestoday == null)
				return "0.00";
			return yestoday.getClose() + "";
		}

		@Override
		public String getPriceNow() {
			if (today == null)
				return "0.00";
			if (priceNow == null) {
				priceNow = initPriceNow();
			}
			return priceNow;

		}

		private String initPriceNow() {
			String now;
			if (today == null) {
				now = "0.00";
			} else {
				double low = today.getLow();
				double high = today.getHigh();
				now = Util.Random.get(low, high) + "";
			}
			return now;

			// if (today == null)
			// return "0.00";
			// String clockNow = timer.getClockNow();
			// StockMarket ins = StockMarket.getInstance();
			// String code = getCode();
			// int date = ins.getDate();
			// PriceGetter fx = new FenShiMingXi2();
			// return fx.getPrice(code, date + "", clockNow);
		}

		@Override
		public String getHighToday() {
			if (today == null)
				return null;
			return today.getHigh() + "";
		}

		@Override
		public String getLowToday() {
			if (today == null)
				return null;
			return today.getLow() + "";
		}

		@Override
		public boolean isDieTing() {
			if (today == null)
				return false;
			boolean dieTing = today.isDieTing();
			return dieTing;
		}

		@Override
		public boolean isZhangTing() {
			if (today == null)
				return false;
			boolean zhangTing = today.isZhangTing();
			return zhangTing;
		}

		@Override
		public double getUp() {
			if (today == null)
				return 0;
			return today.getUp();
		}

	}

	public static class TestStockRequester implements MoniStockRequester {

		@Override
		public List<IStockRecord> request(Set<String> ids) {
			ArrayList<IStockRecord> ls = Lists.newArrayList();
			StockMarket m = StockMarket.getInstance();

			for (String s : ids) {
				Stock2 stock = m.getStock(s);
				IStockRecord build = build(stock);
				ls.add(build);
			}

			return ls;
		}

		private IStockRecord build(Stock2 stock) {
			return new StockRecordAdaptor(stock);
		}

	}

	public static class TestEveryDayLooper implements EveryDayLooper {

		public class DayMoni extends Thread {

			@Override
			public void run() {
				for (int i = 0; i < JIAO_YI_DAY; i++) {
					processTasks();
					StockMarket.getInstance().nextDay();
					// Log.d("nextDay", StockMarket.getInstance().getDate());
				}
			}

			private void processTasks() {
				List<EveryDayTask> as = Lists.newArrayList(tasks);
				Collections.sort(as);
				for (EveryDayTask t : as) {
					try {
						clockNow = t.getTime();
						t.getTask().runSafty();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		public class EveryDayTask implements Comparable<EveryDayTask> {

			private String time;
			private TaskSafety task;

			public EveryDayTask(String time, TaskSafety task) {
				this.time = time;
				this.task = task;
			}

			public TaskSafety getTask() {
				return task;
			}

			public String getTime() {
				return time;
			}

			@Override
			public int compareTo(EveryDayTask o) {
				return getTime().compareTo(o.getTime());
			}
		}

		private DayMoni dayMoni;
		private Set<EveryDayTask> tasks = Sets.newConcurrentHashSet();
		private String clockNow;

		public TestEveryDayLooper() {
			StockMarket m = StockMarket.getInstance();
			m.setDate(DATE_START);
		}

		@Override
		public void loopEveryDay(String time, TaskSafety task) {
			addTask(time, task);
			ensureThreadStart();
		}

		private void addTask(String time, TaskSafety task) {
			tasks.add(new EveryDayTask(time, task));
		}

		private void ensureThreadStart() {

			if (dayMoni == null) {
				dayMoni = new DayMoni();
				dayMoni.start();
			}
		}

		@Override
		public String getClockNow() {
			return clockNow;
		}

		@Override
		public int getDateNow() {
			return StockMarket.getInstance().getDate();
		}
	}

	public static class NormalStockRequester implements MoniStockRequester {

		@Override
		public List<IStockRecord> request(Set<String> ids) {
			ArrayList<IStockRecord> ls = Lists.newArrayList();
			StockRequester r = new StockRequester();
			List<StockRecord> all = r.request(ids);
			for (StockRecord ss : all) {
				ls.add(ss);
			}
			return ls;
		}

	}

	public interface MoniStockRequester {

		List<IStockRecord> request(Set<String> ids);

	}

	public static class NormalEveryDayLooper implements EveryDayLooper {

		private TaskCenter center;

		public NormalEveryDayLooper() {
			center = new TaskCenter();
		}

		@Override
		public void loopEveryDay(String time, TaskSafety task) {
			center.loopEveryDay(time, task);
		}

		@Override
		public String getClockNow() {
			SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
			return sf.format(new Date(System.currentTimeMillis()));
		}

		@Override
		public int getDateNow() {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			String format = sf.format(new Date(System.currentTimeMillis()));
			return new Integer(format);
		}

	}

	public interface EveryDayLooper {

		void loopEveryDay(String time, TaskSafety task);

		/**
		 * @return 例子14:02
		 */
		String getClockNow();

		int getDateNow();
	}

	public class Buy {

		public double price;
		public int count;
		public String id;

	}

	public class BuyTask extends TaskSafety {

		@Override
		public void runSafty() {
			if (STRATEGE.couldBuy()) {
				player.buy();
			}
		}

	}

	public class SellTask extends TaskSafety {

		private boolean isSellMust;

		public SellTask(boolean isSellMust) {
			this.isSellMust = isSellMust;
		}

		@Override
		public void runSafty() {
			player.sell(isSellMust);
		}

	}

	public class MoniTimer {

		private EveryDayLooper looper;

		public void loopEveryDay(String time, TaskSafety task) {
			if (looper == null) {
				this.looper = STRATEGE.createLooper();
			}
			looper.loopEveryDay(time, task);
		}

		public String getClockNow() {
			return looper.getClockNow();
		}

		public EveryDayLooper getLooper() {
			return looper;
		}

		public int getDateNow() {
			return looper.getDateNow();
		}

	}

	public enum Stratege {
		REAL {
			@Override
			public EveryDayLooper createLooper() {
				return new NormalEveryDayLooper();
			}

			@Override
			public boolean couldBuy() {
				return Market.inJiaoYiTime();
			}

			@Override
			public MoniStockRequester createRequester(MoniTimer timer,
					MoniPlayer player) {
				return new NormalStockRequester();
			}
		},
		TEST_BY_MONGODB_DATA {
			@Override
			public EveryDayLooper createLooper() {
				return new TestEveryDayLooper();
			}

			@Override
			public boolean couldBuy() {
				return true;
			}

			@Override
			public MoniStockRequester createRequester(MoniTimer timer,
					MoniPlayer player) {
				return new TestStockRequester();
			}
		},
		TEST_BY_CSV_DATA {

			@Override
			public EveryDayLooper createLooper() {
				return new CsvDataLooper();
			}

			@Override
			public boolean couldBuy() {
				return true;
			}

			@Override
			public MoniStockRequester createRequester(MoniTimer timer,
					MoniPlayer player) {
				return new CsvRequester(timer);
			}
		},
		;

		public abstract EveryDayLooper createLooper();

		public abstract boolean couldBuy();

		public abstract MoniStockRequester createRequester(MoniTimer timer,
				MoniPlayer player);
	}

	/**
	 * 运行策略
	 */
	// private static Stratege STRATEGE = Stratege.TEST_BY_MONGODB_DATA;
	private static Stratege STRATEGE = Stratege.TEST_BY_CSV_DATA;

	private static final int DATE_START = 20080105;

	private static double BUY_SHOUXU = 0.00025;

	private static final int JIAO_YI_DAY = 248;

	Map<String, SinaStock> sinaStockCache = Maps.newHashMap();

	public class MoniPlayer {

		private MoniPlayerDto dto;

		private double profitRmb;

		public MoniPlayer() {
			MoniPlayerDao dao = Daos.getMoniPlayerDao();
			dto = dao.get("player");
			if (dto == null) {
				dto = new MoniPlayerDto();
				dto.setId("player");
				dto.setRmb(1000000);
				dao.save(dto);
			}

		}

		public void buy() {

			List<IStockRecord> stocks = requestSinaStocks();

			int faildTimes = 0;

			while (!stocks.isEmpty()) {
				IStockRecord s = stocks.get(0);

				Double priceNow = new Double(s.getPriceNow());

				if (priceNow < 0.001 || s.isZhangTing() || s.isDieTing()) {
					stocks.remove(s);
					continue;
				}

				Buy buy = getBuyThisTime(s);

				if (buy.count == 0) {
					stocks.remove(s);
					continue;
				}

				double priceAll = buy(buy.id, s.getDate(), buy.price, buy.count);

				boolean isSuccesful = priceAll > 0.01;// 是否买入成功

				if (!isSuccesful) {
					faildTimes++;
				} else {
					faildTimes = 0;
				}

				if (faildTimes >= 10)
					break;

				moveFirstToLast(stocks);
			}
		}

		public void sell(boolean isSellMust) {
			requestSinaStocks();
			List<MyStockDto> stocks = Lists.newArrayList(getStocks());
			for (MyStockDto s : stocks) {
				sell(s, isSellMust);
			}
		}

		private void sell(MyStockDto s, boolean isSellMust) {
			String id = s.getId();
			double priceNow = getPriceNow(id);

			// if (isTooManyDays(s)) {
			// SinaStock dto = sinaStockCache.get(id);
			// double up = dto.getUp();
			// if (up > 0) {
			// sell(s, priceNow);
			// }
			// return;
			// }
			//
			// double buyPrice = s.getBuyPrice();
			boolean isAOtherDay = isAOtherDay(s);
			// if (priceNow > buyPrice && canSell) {
			if (isAOtherDay) {

				if (isSellMust) {

					if (priceNow != 0)
						sell(s, priceNow);
				} else {
					double buyPrice = s.getBuyPrice();
					if (priceNow > buyPrice)
						sell(s, priceNow);
				}
			}
		}

		public boolean isTooManyDays(MyStockDto s) {
			String buyDate = s.getBuyDate();
			String now = timer.getDateNow() + "";

			int space = Market.getSpace(buyDate, now);
			return space >= 5;
		}

		private boolean isAOtherDay(MyStockDto s) {
			int dateNow = timer.getDateNow();
			String b = s.getBuyDate();
			int buyDate = new Integer(b);
			return dateNow > buyDate;
		}

		private void sell(MyStockDto s, double priceNow) {
			dto.getStocks().remove(s);

			double buyPrice = s.getBuyPrice();
			if ((priceNow - buyPrice) / buyPrice < -0.22) {
				priceNow = buyPrice;
			}
			addRmb(s, priceNow);
			sLog(s, priceNow);
		}

		private void sLog(MyStockDto d, double priceNow) {
			StringPrinter out = new StringPrinter();
			String head = "S";

			buildHead(d, out, head);
			out.print(" sp:" + Market.toPriceString(priceNow));
			out.print(" P:" + getProfit(priceNow, d));

			getTradeLogs().add(out.toString());
			Daos.getMoniPlayerDao().save(dto);
			Log.d(out);
		}

		private void buildHead(MyStockDto d, StringPrinter out, String head) {
			// double sx = d.getBuyPrice() * d.getCount() * BUY_SHOUXU;

			out.print(head + " id:" + d.getId());
			out.print(" now:" + timer.getDateNow() + "_" + timer.getClockNow());
			out.print(" bd:" + d.getBuyDate());
			out.print(" prmb:" + Market.toPriceString(profitRmb));
			out.print(" bp:" + Market.toPriceString(d.getBuyPrice()));
			out.print(" cnt:" + d.getCount());
			out.print(" all:" + Market.toPriceString(player.getAsset()));
			// out.print(" rmb:" + Market.toPriceString(dto.getRmb()));
			// out.print(" sx:" + Market.toPriceString(sx));
			// out.print(" c:" + getStocks().size());

		}

		private String createBuyLog(MyStockDto d) {
			StringPrinter out = new StringPrinter();
			String head = "B";

			buildHead(d, out, head);
			getTradeLogs().add(out.toString());
			return out.toString();
		}

		private String getProfit(double priceNow, MyStockDto d) {
			double buyPrice = d.getBuyPrice();
			double p = (priceNow - buyPrice) / buyPrice;
			double rmbProfit = (priceNow - buyPrice) * d.getCount();
			profitRmb += rmbProfit;
			String a = Market.toPriceString(rmbProfit);
			String b = Market.toPriceString(p * 100);
			return a + "|" + b + "%";
		}

		private void addRmb(MyStockDto s, double priceNow) {
			int c = s.getCount();
			double all = priceNow * c;
			all *= (1 - BUY_SHOUXU);
			dto.setRmb(dto.getRmb() + all);
		}

		private List<IStockRecord> requestSinaStocks() {
			MoniStockRequester r = STRATEGE.createRequester(timer, player);
			Set<String> ids = Market.getSelectStocks();
			List<IStockRecord> all = r.request(ids);
			updateCurrentPriceInDb(all);
			return all;
		}

		private void updateCurrentPriceInDb(List<IStockRecord> all) {
			for (IStockRecord d : all) {

				SinaStock dto = new SinaStock();
				dto.setCode(d.getCode());
				dto.setPriceNow(new Double(d.getPriceNow()));
				dto.setUp(d.getUp());
				sinaStockCache.put(d.getCode(), dto);
				// Log.d(d.getCode(), d.getDate(), d.getPriceNow());
			}
		}

		private double buy(String id, String date, double price, int count) {
			if (price < 0.01) {
				return 0;
			}

			if (count == 0)
				return 0;

			double rmbNeedAll = price * count * (1 + BUY_SHOUXU);
			if (dto.getRmb() < rmbNeedAll)
				return 0;

			reduceRmb(rmbNeedAll);

			addStockToMyPacket(id, date, price, count);

			return rmbNeedAll;
		}

		private void addStockToMyPacket(String id, String date, double price,
				int count) {
			List<MyStockDto> stocks = getStocks();
			if (stocks == null) {
				stocks = Lists.newArrayList();
				dto.setStocks(stocks);
			}

			MyStockDto d = new MyStockDto();
			d.setBuyDate(date);
			d.setBuyPrice(price);
			d.setCount(count);
			d.setId(id);

			stocks.add(d);

			bLog(d);
		}

		private void bLog(MyStockDto d) {
			String log = createBuyLog(d);
			Log.d(log);
			List<String> logs = getTradeLogs();
			logs.add(log);
			Daos.getMoniPlayerDao().save(dto);

			// new ImageSaver().saveToImage(buyDay, sellDay);
		}

		private List<String> getTradeLogs() {
			List<String> logs = dto.getTradeLogs();
			if (logs == null) {
				logs = Lists.newArrayList();
				dto.setTradeLogs(logs);
			}
			return logs;
		}

		private void reduceRmb(double d) {
			if (dto.getRmb() < d)
				throw new IllegalArgumentException("rmb not enouph! need:" + d
						+ " you have:" + dto.getRmb());
			dto.setRmb(dto.getRmb() - d);
			Daos.getMoniPlayerDao().save(dto);
		}

		private <T> void moveFirstToLast(List<T> stocks) {
			T remove = stocks.remove(0);
			stocks.add(remove);
		}

		public Buy getBuyThisTime(IStockRecord stockRecord) {

			Buy buy = new Buy();
			if (stockRecord == null)
				return buy;

			String pNow = stockRecord.getPriceNow();
			double priceNow = new Double(pNow);

			buy.id = stockRecord.getCode();

			double buyUnit = getBuyUnit();

			String id = stockRecord.getCode();

			double asset = getAsset(id);
			buyUnit -= asset; // 减去已经持仓额度
			double rmb = dto.getRmb();

			String yes = stockRecord.getCloseYestoday();
			Double closeYestoday = new Double(yes);
			if (Math.abs(closeYestoday) < 0.001)
				return buy;

			double buyUp = -7.9;
			double buyCondition = 1 + buyUp / 100;

			double buyPriceHigh = closeYestoday * buyCondition;

			if (priceNow > buyPriceHigh) // 比预定价还高的话, 无法买入
				return buy;

			buy.price = new Double(pNow);

			int canBuyCount = (int) ((rmb / buy.price)) / 100 * 100;
			int wantBuyCount = (int) ((buyUnit / buy.price)) / 100 * 100;

			buy.count = Util.Math.min(canBuyCount, wantBuyCount);
			buy.count = Math.max(0, buy.count);

			return buy;
		}

		public double getBuyUnit() {

			double asset = getAsset();

			double buyUnit = asset * 0.25; // 钱足够的情况下, 每个购买单元金额}
			return buyUnit;
		}

		/**
		 * 大致价值
		 */
		public double getAsset() {
			List<MyStockDto> stocks = getStocks();

			double sum = 0;
			for (MyStockDto dto : stocks) {
				sum += dto.getCount() * dto.getBuyPrice();
			}
			return sum + dto.getRmb();
		}

		private List<MyStockDto> getStocks() {
			List<MyStockDto> stocks = dto.getStocks();
			if (stocks == null) {
				stocks = Lists.newArrayList();
				dto.setStocks(stocks);
			}
			return stocks;
		}

		/**
		 * 当前持仓大致价值
		 */
		public double getAsset(String id) {
			List<MyStockDto> stocks = getStocks();
			int sum = 0;
			for (MyStockDto d : stocks) {
				if (d.getId().equals(id))
					sum += d.getCount() * d.getBuyPrice();
			}
			return sum;
		}

		private double getPriceNow(String id) {
			SinaStock s = sinaStockCache.get(id);
			return s.getPriceNow();
		}

	}

	MoniPlayer player = new MoniPlayer();
	MoniTimer timer = new MoniTimer();

	public static void main(String[] args) throws FileNotFoundException {
		Daos.setProperties(new MongoDbPropertiesImpl());
		if (STRATEGE == Stratege.TEST_BY_MONGODB_DATA
				|| STRATEGE == Stratege.TEST_BY_CSV_DATA)
			Daos.getMoniPlayerDao().clear();
		Log.setOut(new PrintToConsoleAndFile());
		new AppMoniBuy1().run();
	}

	private void run() {

		sell("11:00", false);
		sell("11:05", false);
		sell("11:15", false);
		sell("11:25", false);
		sell("13:10", false);
		sell("13:20", false);
		sell("13:30", false);
		sell("13:35", false);
		sell("14:00", true);

		buy("13:40");
		buy("13:50");
		buy("14:25");
	}

	private void buy(String buyTime) {
		timer.loopEveryDay(buyTime, new BuyTask());
	}

	private void sell(String sellTime, boolean isSellMust) {
		timer.loopEveryDay(sellTime, new SellTask(isSellMust));
	}
}
