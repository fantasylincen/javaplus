package cn.javaplus.stock.quotes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.log.Log;
import cn.javaplus.stock.apps.AppQuotesDownloader;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * hang qing shuju chu cun dao wenjian
 */
public class FileSaver implements Runnable {

	private QuotesRequester requester;
	private List<SaveListener> listeners = Lists.newArrayList();

	public FileSaver(QuotesRequester requester) {
		this.requester = requester;
	}

	@Override
	public void run() {
		while (true) {
			if (AppQuotesDownloader.DEBUG || Market.inJiaoYiTime()) {
				List<StockRecord> records = pollAll(requester.getRecords());
				if (records != null && !records.isEmpty()) {
					saveToFile(records);
					Log.d("save over");
				}
			}
			Util.Thread.sleep(1000);
		}
	}

	private List<StockRecord> pollAll(ConcurrentLinkedQueue<StockRecord> records) {
		ArrayList<StockRecord> ls = Lists.newArrayList();
		while (!records.isEmpty()) {
			StockRecord r = records.poll();
			ls.add(r);
		}
		return ls;
	}

	private void saveToFile(List<StockRecord> records) {
		long t1 = System.currentTimeMillis();
		for (StockRecord record : records) {
			String fileName = buildFileName(record);
			Util.File.append(fileName, record.toFormatString() + "\r");
		}
		for (SaveListener l : listeners) {
			l.onSave(records);
		}
		Log.d("save file use time millis", System.currentTimeMillis() - t1);
	}

	private String buildFileName(IStockRecord r) {
		String code = r.getCode();
		String date = r.getDate();
		return Market.WORKSPACE_DIR + "/" + code + "." + date + ".txt";
	}

	public void addListener(SaveListener listener) {
		this.listeners.add(listener);
	}
}
