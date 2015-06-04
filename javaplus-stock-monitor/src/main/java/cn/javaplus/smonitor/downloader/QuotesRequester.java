package cn.javaplus.smonitor.downloader;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

/**
 * hang qing qing qiu qi
 */
public class QuotesRequester implements Runnable {

	private static QuotesRequester instance;
	public static QuotesRequester getInstance() {
		if(instance == null) {
			instance = new QuotesRequester();
		}
		return instance;
	}
	
	private QuotesRequester() {
	}
	

	private ConcurrentLinkedQueue<StockRecord> records = new ConcurrentLinkedQueue<>();

	@Override
	public void run() {
		Util.Thread.sleep(3000);
		while (true) {
			if (Market.inJiaoYiTime()) {
				updateStocks();
				Util.Thread.sleep(60000);
			} else {
				Util.Thread.sleep(1000);
			}
		}
	}

	public void downloadStocks() {
		updateStocks();
		FileSaver.getInstance().updateToFiles();
	}
	
	private void updateStocks() {
		StockRequester r = new StockRequester();
		SMonitor ins = SMonitor.getInstance();
		List<StockRecord> rs = r.request(ins.getStockIds());
		Log.d("request over");
		records.addAll(rs);		
	}

	public ConcurrentLinkedQueue<StockRecord> getRecords() {
		return records;
	}

}
