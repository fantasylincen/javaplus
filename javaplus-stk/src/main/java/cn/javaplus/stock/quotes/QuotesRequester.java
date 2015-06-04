package cn.javaplus.stock.quotes;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.log.Log;
import cn.javaplus.stock.apps.AppQuotesDownloader;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

/**
 * hang qing qing qiu qi
 */
public class QuotesRequester implements Runnable {

	private ConcurrentLinkedQueue<StockRecord> records = new ConcurrentLinkedQueue<>();
	private CodesUpdate u;

	public QuotesRequester(CodesUpdate u) {
		this.u = u;
	}

	@Override
	public void run() {
		Util.Thread.sleep(3000);
		while (true) {
			if (AppQuotesDownloader.DEBUG || Market.inJiaoYiTime()) {
				List<StockRecord> rs = new StockRequester().request(u.getAllCodes());
				Log.d("request over");
				records.addAll(rs);
				Util.Thread.sleep(30000);
			} else {
				Util.Thread.sleep(1000);
			}
		}
	}

	public ConcurrentLinkedQueue<StockRecord> getRecords() {
		return records;
	}

}
