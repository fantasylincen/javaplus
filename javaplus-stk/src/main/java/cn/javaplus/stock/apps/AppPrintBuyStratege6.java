package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.moni.MyStock;
import cn.javaplus.stock.moni.MyStock.Sell;
import cn.javaplus.stock.moni.Player;
import cn.javaplus.stock.moni.ProfitOfSingleStock;
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

	public Set<String> testStocks = Market.getSelectStocks();

	/**
	 * 最大平仓日
	 */
	public static int MAX_SELL_DAY;

	public static void main(String[] args) throws FileNotFoundException {
		Daos.setProperties(new MongoDbPropertiesImpl());
		testBuy();
	}

	Avg assetEndAvg = new Avg();
	private ArrayList<Stock2> allStocks;

	static void testBuy() throws FileNotFoundException {

		Log.setOut(new PrintToConsoleAndFile());

		double buyUp = -2.9;
		double buyCondition = 1 + buyUp / 100;

		AppPrintBuyStratege6 s = new AppPrintBuyStratege6();
//		while (true) {
			s.run(buyCondition);
//		}
	}

	private double run(double buyCondition) {
		int year = 1;
		int startYear = Util.Random.get(2001, 2014 - year);
		START_DATE = new Integer(startYear + "0105");
		END_DATE = new Integer((startYear + year - 1) + "1231");
		RMB_START = 1000000;

		MAX_SELL_DAY = 4;

		StockMarket market = StockMarket.getInstance();
		market.setDate(START_DATE);

		Player player = new Player();
		player.setRmb(RMB_START);

		while (market.getDate() <= END_DATE) {

			sell(player);
			List<Stock2> datas = findCanBuy();
			buyAvg(datas, player, buyCondition);
			market.nextDay();
		}

		double ac = player.getAll();

		String asset = Market.toPriceString2(ac);
		assetEndAvg.add(ac);
		int st = player.getSellTimes();
		Log.d(START_DATE, END_DATE, Market.toPriceString2(RMB_START), asset, st,
				Market.toPriceString2(assetEndAvg.getValue()));
		// Log.d(getProfitOfSingles(player).get(0));
		return ac;
	}

	private List<ProfitOfSingleStock> getProfitOfSingles(Player player) {
		Map<String, ProfitOfSingleStock> p = player.getProfitOfSingleStock();
		List<ProfitOfSingleStock> values = Lists.newArrayList(p.values());
		Collections.sort(values);
		return values;
	}

	private List<Double> builds(Collection<ProfitOfSingleStock> values) {
		return Lists.newArrayList();
	}

	
	private List<Stock2> findCanBuy() {

		List<Stock2> stocks = getSelectStocks();

		// StockMarket market = StockMarket.getInstance();
		// List<Stock2> stocks = market.getStocks();

		ArrayList<Stock2> ss = Lists.newArrayList();
		for (Stock2 s : stocks) {
			ss.add(s);
		}
		Util.Collection.upset(ss);
		return Util.Collection.sub(ss, 200);
	}

	private List<Stock2> getSelectStocks() {
		if (allStocks != null)
			return allStocks;
		StockMarket market = StockMarket.getInstance();

		allStocks = Lists.newArrayList();
		for (String id : testStocks) {
			Stock2 stock = market.getStock(id);
			if (stock != null)
				allStocks.add(stock);
		}
		return allStocks;
	}

	public void sell(Player player) {
		List<MyStock> stocks = player.getStocks();

		List<MyStock> ss = Lists.newArrayList(stocks);

		for (MyStock s : ss) {
			Sell sell = s.getSell();
			if (sell.getPrice() > 0.0001) {
				double allPrice = sell.getAllPrice();
				player.sell(s, allPrice);
				stocks.remove(s);
			}
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

		double low = data.getLow();

		double closeYestoday = yes.getClose();
		double buyPriceHigh = closeYestoday * buyCondition;

		if (low <= 0 || low > buyPriceHigh) // 比预定价还高的话, 无法买入
			return buy;

		buy.price = Util.Random.get(low, buyPriceHigh);

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
