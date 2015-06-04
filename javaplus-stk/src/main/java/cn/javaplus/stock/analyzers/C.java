package cn.javaplus.stock.analyzers;

import java.util.Set;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;

/**
 * 收盘价 小于 DAY日最高价  * PERCENT
 */
public class C implements AnalyzeStrategy {

	private static final double ZHEN_FU = 0.005;
	private static final int DAY = 33;
	private static final double PERCENT = 0.75;

	public boolean conform(Stock1 stock) {
		
		
//		Set<String> s = Sets.newHashSet("150028", "150067", "150142", "160131", "160513", "160720", "161117", "161119", "161614", "161815", "161820", "161821", "163824", "163827", "166011", "200018", "120602", "122616", "122719", "122779", "124056", "124122", "124517", "600213", "600656", "900911", "900915", "900932");
//		if(s.contains(stock.getId()))
//			return false;
		
		if(!startWith(stock.getId(), Sets.newHashSet("00", "20", "30", "50", "60", "90"))) {
			return false;
		}
		
//		Log.d("正在分析:" + stock.getId());
		
		double upAvg = stock.getUpAvg(DAY); // 60日平均涨幅绝对值
		if(upAvg < ZHEN_FU) {
			return false;
		}

		double h = stock.getHighest(DAY);
		OneDayData last = stock.getLast();
		if (last == null || !last.isNear())
			return false;
		return last.getClose() / h <= PERCENT;
	}

	private boolean startWith(String id, Set<String> set) {
		for (String ss : set) {
			if(id.startsWith(ss))
				return true;
		}
		return false;
	}
	
}
