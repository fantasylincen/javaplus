package cn.javaplus.smonitor.init;

import cn.javaplus.smonitor.downloader.Buy1Monitor;
import cn.javaplus.smonitor.downloader.FileSaver;
import cn.javaplus.smonitor.downloader.QuotesRequester;

public final class ThreadStockDataDownloader extends Thread {

	private static Thread instance;

	boolean isStart;

	public void run() {
		QuotesRequester r = QuotesRequester.getInstance();
		Buy1Monitor bm = new Buy1Monitor();
		FileSaver s = new FileSaver(r);
		new Thread(r).start();
		new Thread(s).start();
		new Thread(bm).start();
	}

	@Override
	public synchronized void start() {
		if (!isStart) {
			super.start();
			isStart = true;
		}
	}

	public static Thread getInstance() {
		if (instance == null)
			instance = new ThreadStockDataDownloader();
		return instance;
	}

}