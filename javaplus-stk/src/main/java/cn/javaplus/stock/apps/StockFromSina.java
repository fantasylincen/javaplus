package cn.javaplus.stock.apps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import cn.javaplus.log.Log;
import cn.javaplus.stock.quotes.StockRecord;
import cn.javaplus.stock.quotes.StockRequester;
import cn.javaplus.stock.util.Market;

public class StockFromSina implements Runnable {

	@Override
	public void run() {
		List<StockRecord> rs = new StockRequester().request(buildCodes());		
		for (StockRecord ss : rs) {
			Log.d(ss.getCode(), ss.getBuyPrice1(), ss.getPriceNow(), ss.getSellPrice1(), ss.getCloseYestoday());
		}
	}

	private Set<String> buildCodes() {
		Set<String> ss = Market.getSelectStocks();
		HashSet<String> ssss = Sets.newHashSet();
		for (String sqid : ss) {
			ssss.add(Market.getCode(sqid));
		}
		return ssss;
		
	}

}
