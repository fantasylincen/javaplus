package cn.javaplus.stock.analyzers;

import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;

/**
 * 近N日除权的股票
 */
public class E implements AnalyzeStrategy {

	private static final int DAY = 5;

	public boolean conform(Stock1 stock) {

		Log.d("正在分析:" + stock.getId());

		OneDayData last = stock.getLast();

		if (last == null)
			return false;

		if (!last.isNear()) {
			return false;
		}

		for (int i = 0; i < DAY; i++) {

			if (last == null)
				return false;

			if (last.hasChuQuan())
				return true;

			last = last.getYestoday();
		}
		return false;
	}
}
