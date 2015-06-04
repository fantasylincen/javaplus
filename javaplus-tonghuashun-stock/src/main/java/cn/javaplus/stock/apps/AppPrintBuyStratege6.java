package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.moni.MyStock;
import cn.javaplus.stock.moni.Player;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

/**
 * buy depend on macd
 */
public class AppPrintBuyStratege6 {

	public class Buy {
		int count;
		double price;
	}

	public static int START_DATE;
	public static int END_DATE;
	public static double RMB_START;

	/**
	 * 最大平仓日
	 */
	public static int MAX_SELL_DAY;

	/** 总资产的百分之多少作为一个最低购买单元 */
	public static double BUY_UNIT_PERCENT;

	public static void main(String[] args) throws FileNotFoundException {
		Daos.setProperties(new MongoDbPropertiesImpl());
		testBuy();
	}

	static void testBuy() throws FileNotFoundException {

		Log.setOut(new PrintToConsoleAndFile());

		int times = 0;
		double buyCondition = 0.9795;
		for (double sellCondition = 1.0143; sellCondition < 1.017; sellCondition += 0.0001) {
			times++;
			double avgAssetOfTimes = run(buyCondition, sellCondition, 50);
		}
	}

	private static double run(double buyCondition, double sellCondition,
			int times) {
		Avg a = new Avg();
		for (int i = 0; i < times; i++) {
			a.add(new AppPrintBuyStratege6().run(buyCondition, sellCondition));
		}
		return a.getValue();
	}

	private double run(double buyCondition, double sellCondition) {
		int startYear = Util.Random.get(2004, 2004);
		int year = 1;
		START_DATE = new Integer(startYear + "0105");
		END_DATE = new Integer((startYear + year - 1) + "1231");
		RMB_START = 2000000;

		MAX_SELL_DAY = 5;
		BUY_UNIT_PERCENT = 0.1;

		StockMarket market = StockMarket.getInstance();
		market.setDate(START_DATE);

		Player player = new Player();
		player.setRmb(RMB_START);

		while (market.getDate() <= END_DATE) {

			sell(player, sellCondition);
			List<Stock2> datas = findCanBuy();
			buyAvg(datas, player, buyCondition);
			market.nextDay();
		}

		double ac = player.getAsset();

		String asset = getPrice(ac);

		int st = player.getSellTimes();
//		Log.d(START_DATE, END_DATE, getPrice(RMB_START), asset, st);
		return ac;
	}

	private String getPrice(double ac) {
		return Market.toPriceString(Math.rint(ac / 10000)) + " W";
	}

	private List<Stock2> findCanBuy() {

		StockMarket market = StockMarket.getInstance();

		List<Stock2> stocks = market.getStocks();

		ArrayList<Stock2> ss = Lists.newArrayList();
		for (Stock2 s : stocks) {
			DayData d = getCurrentDay(s);
			if (isMacdWill(d))
				ss.add(s);
		}
		Util.Collection.upset(ss);
		return Util.Collection.sub(ss, 80);
	}

	private boolean isMacdWill(DayData dt) {
		if (dt == null)
			return false;
		return true;
	}

	public void sell(Player player, double sellCondition) {
		List<MyStock> stocks = player.getStocks();

		ArrayList<MyStock> ss = Lists.newArrayList();
		for (MyStock s : stocks) {
			if (canSell(s, sellCondition)) {
				ss.add(s);
			}
		}

		for (MyStock s : ss) {
			double all = getSellPrice(s, sellCondition) * s.getCount();
			player.sell(s, all);
		}

	}

	public Buy getBuyThisTime(Player player, DayData data, double buyCondition) {

		Buy buy = new Buy();

		if (data == null)
			return buy;

		double buyUnit = player.getBuyUnit(); // 钱足够的情况下, 每个购买单元金额

		String id = data.getId();

		double asset = player.getAsset(id);
		buyUnit -= asset; // 减去已经持仓额度
		double rmb = player.getRmb();

		DayData yes = data.getYestoday();
		if (yes == null)
			return buy;

		double closeYestoday = yes.getClose();
		buy.price = closeYestoday * buyCondition;
		double low = data.getLow();

		if (low > buy.price) {// 如果预定买入价比当天最小值还低, 那么无法买入
			return buy;
		}

		double open = data.getOpen();
		if (open < buy.price)
			buy.price = open;

		if (buy.price == 0) {
			return buy;
		}

		int canBuyCount = (int) ((rmb / buy.price)) / 100 * 100;
		int wantBuyCount = (int) ((buyUnit / buy.price)) / 100 * 100;

		buy.count = Util.Math.min(canBuyCount, wantBuyCount);
		buy.count = Math.max(0, buy.count);

		return buy;
	}

	public DayData getCurrentDay(Stock2 stock) {
		int date = StockMarket.getInstance().getDate();
		return stock.getByDate(date + "");
	}

	private boolean canSell(MyStock s, double sellCondition) {
		DayData day = s.getToday();

		if (day == null)
			return false;

		double high = day.getHigh();

		double buyPrice = s.getSinglePrice();

		if (day.isDieTing() || day.isZhangTing()) {
			return false;
		}

		if (isTooManyDays(s)) {
			return true;
		}

		return high / buyPrice > sellCondition;
	}

	private double getSellPrice(MyStock s, double sellCondition) {

		DayData day = s.getToday();
		double open = day.getOpen();

		if (isTooManyDays(s))
			return open;

		double sprice = s.getSinglePrice();

		double openSPercent = open / sprice;
		if (openSPercent > sellCondition) {
			return open;
		}

		double p = sprice * sellCondition;
		return p;
	}

	private boolean isTooManyDays(MyStock s) {
		DayData cd = s.getToday();
		DayData bd = s.getBuyDay();
		int space = Market.getSpace(cd, bd);
		return space >= AppPrintBuyStratege6.MAX_SELL_DAY;
	}

	/**
	 * 均衡买入
	 * 
	 * @param buyCondition
	 * 
	 * @param stratege
	 */
	private void buyAvg(List<Stock2> stocks, Player player, double buyCondition) {

		int faildTimes = 0;

		while (!stocks.isEmpty()) {
			Stock2 data = stocks.get(0);

			DayData dt = getCurrentDay(data);

			Buy buy = getBuyThisTime(player, dt, buyCondition);

			if (buy.count == 0) { // 买不起 就不买
				stocks.remove(data);
				continue;
			}

			double priceAll = buy(player, dt, buy);

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

	private static double buy(Player player, DayData dt, Buy buy) {

		if (buy.price < 0.01) {
			return 0;
		}

		return player.buy(dt, buy.count, buy.price);
	}

	private static void moveFirstToLast(List<Stock2> stocks) {
		Stock2 remove = stocks.remove(0);
		stocks.add(remove);
	}

}
