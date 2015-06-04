package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.moni.IBuyStratege;
import cn.javaplus.stock.moni.IStockRecommend;
import cn.javaplus.stock.moni.MoniBuyStratege;
import cn.javaplus.stock.moni.Player;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.moni.TradeLog;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.ImageSaver;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

/**
 * buy depend on macd
 */
public class AppPrintBuyStratege4 {

	public static final int START_DATE = 19980101;
	public static final int END_DATE = 20150101;
	public static final double RMB_START = 100000;


	/** 第几根macd红线卖出 */
	public static int WITCH_RED_MACD_SELL = 4;

	/** 存图概率 */
	public static double GENERATE_IMAGE_PROBABILITY = 0.01;

	/** 第几根macd绿线买入 */
	public static int WITCH_GREEN_MACD_BUY = 4;

	/** 是否打印全部日志 */
	public static boolean IS_PRINT_ALL_LOG = false;

	/** 是否将日志打印到文件 */
	private static boolean IS_LOG_TO_FILE = false;

	public static void main(String[] args) throws FileNotFoundException {

		Daos.setProperties(new MongoDbPropertiesImpl());

		StockMarket market = StockMarket.getInstance();
		market.setDate(START_DATE);

		Player player = new Player();
		player.setRmb(RMB_START);

		if (IS_LOG_TO_FILE)
			System.setOut(new PrintStream("xx.csv"));

		while (market.getDate() <= END_DATE) {

			IBuyStratege stratege = new MoniBuyStratege();
			stratege.sell(player);
			List<Stock2> stocks = market.getStocks();
			List<IStockRecommend> datas = stratege.findBestSorted(stocks);
			buyAvg(datas, player, stratege);
			market.nextDay();
		}

//		printLogs(player);
		Log.d("sell times", player.getSellTimes());
	}

//	private static void printLogs(Player player) {
//		List<TradeLog> logs = player.getTradeLogs();
//
//		int count = 0;
//		for (TradeLog log : logs) {
//			if (IS_PRINT_ALL_LOG || count++ % 200 == 0) {
//				printLog(log);
//			}
//			saveImage(log);
//		}
//	}

	public static void printLog(TradeLog log) {
		if (log.isBuy())
			return;

		String bs = log.isBuy() ? "BUY" : "SEL";
		String id = log.getStockId();
		String prc = "price:" + log.getPrice();
		String cnt = "count:" + log.getCount();
		String asset = "asset:" + Math.rint(new Double(log.getAsset()) / 100)
				/ 100 + "W";
		String rmb = "rmb:" + Market.toPriceString(log.getRmbRemain());
		String profitPercent = "p.percent:"
				+ Market.toPriceString(log.getProfitPercent() * 100) + " %";
		String profitRmb = "p.rmb:" + Market.toPriceString(log.getRmbProfit());
		int date = log.getDate();

		prc = fillSpace(prc, 12);
		cnt = fillSpace(cnt, 12);
		asset = fillSpace(asset, 15);
		rmb = fillSpace(rmb, 15);
		profitPercent = fillSpace(profitPercent, 25);
		profitRmb = fillSpace(profitRmb, 15);

		Log.d(date, bs, id, prc, cnt, asset, rmb, profitPercent, profitRmb);

	}

	private static void saveImage(TradeLog log) {
		if (!log.isBuy() && Util.Random.isHappen(GENERATE_IMAGE_PROBABILITY)) {
			DayData buyDay = log.getBuyDay();
			DayData currentDay = log.getSellDay();
			new ImageSaver().saveToImage(buyDay, currentDay);
		}
	}

	private static String fillSpace(String prc, int count) {
		return Util.Str.fillSpace(prc, count);
	}

	/**
	 * 均衡买入
	 * 
	 * @param stratege
	 */
	private static void buyAvg(List<IStockRecommend> datas, Player player,
			IBuyStratege stratege) {

		int faildTimes = 0;

		while (!datas.isEmpty()) {
			IStockRecommend data = datas.get(0);

			DayData dt = data.getCurrentDay();

			int count = stratege.getBuyCountThisTime(player, dt);

			if (count == 0) { // 买不起 就不买
				datas.remove(data);
				continue;
			}

			double priceAll = player.buy(dt, count);

			boolean isSuccesful = priceAll > 0.01;// 是否买入成功

			if (!isSuccesful) {
				faildTimes++;
			} else {
				faildTimes = 0;
			}

			if (faildTimes >= 10)
				break;

			moveFirstToLast(datas);
		}
	}

	private static void moveFirstToLast(List<IStockRecommend> datas) {
		IStockRecommend remove = datas.remove(0);
		datas.add(remove);
	}

}
