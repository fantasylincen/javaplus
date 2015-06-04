package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.stock.ProfitAnalyzer;

/**
 * buy depend on macd
 */
public class AppPrintBuyStratege3 {

	/** 存图概率 */
	public static final double GENERATE_IMAGE_PROBABILITY = 0.005f;

	/** 买入概率 */
	public static final double BUY_PROBABILITY = 0.1;

	/** 股票数量 */
	public static final int STOCK_COUNT = 600;

	public static void main(String[] args) throws FileNotFoundException {

		Daos.setProperties(new MongoDbPropertiesImpl());

		for (int i = 0; i < 1; i++) {

			doManyTimes();
		}

	}
//TODO 第二根绿线后 继续下跌的, 继续买入
//
	private static void doManyTimes() {

		final int times = 1;

//		int dateStart = 19930101;

		double maxProfit = Double.MIN_VALUE;
		BuyStratege maxBs = null;

		Avg profit = new Avg();
		
		int sellDay = 1;
		for (int i = 0; i < times; i++) {

			BuyStratege bs = new BuyStratege();

			bs.setWitchGreenMacdLineToBuy(2);
			bs.setSellDayAfterRed(sellDay);
//			bs.setDateScope(dateStart, dateStart + 10000);

			List<Profit> nianHua = bs.run();

			ProfitAnalyzer a = new ProfitAnalyzer(nianHua);

			double prof = printResult(a, bs);
			profit.add(prof);
			if (prof > maxProfit) {
				maxProfit = prof;
				maxBs = bs;
			}
			sellDay++;
//			dateStart += 10000;
		}
		Log.d("max", maxProfit, "avg profit", profit.getValue() + " %", maxBs);
	}

	private static double printResult(ProfitAnalyzer a, BuyStratege bs) {
		Log.d(a.getProfitYear(), a.getCount(), bs);
		return new Double(a.getProfitYear().replace("%", ""));
	}

}
