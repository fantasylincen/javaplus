package cn.javaplus.stock.analyzers;


import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;

/**
 * 收盘价小于60日均价
 */
public class A implements AnalyzeStrategy {

	public boolean conform(Stock1 stock) {
		Log.d("正在分析:" + stock.getId());
		
		double avg = stock.getAvg(60);
		OneDayData last = stock.getLast();
		if (last == null || !last.isNear())
			return false;
		return last.getClose() < avg;
	}

}
