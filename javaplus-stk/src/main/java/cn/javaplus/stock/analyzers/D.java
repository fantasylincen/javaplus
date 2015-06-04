package cn.javaplus.stock.analyzers;

import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.time.Time;

public class D implements AnalyzeStrategy {

	private static final int DAY = 25;

	public D() {
		Log.d("停牌超过DAY日");
	}
	
	public boolean conform(Stock1 stock) {
		
		if (!startsWith(stock.getId(), "60", "30", "00", "20", "90")) {
			return false;
		}
		
//		Log.d("正在分析:" + stock.getId());
		
		OneDayData last = stock.getLast();
		if (last == null)
			return false;

		long dateMillis = last.getDateMillis();
		
		return System.currentTimeMillis() - dateMillis > Time.MILES_ONE_DAY * DAY;
		
	}
	
	private boolean startsWith(String id, String... s) {
		for (String ss : s) {
			if (id.startsWith(ss))
				return true;
		}
		return false;
	}
}
