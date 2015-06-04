package cn.javaplus.stock.apps;

import cn.javaplus.stock.quotes.CanBuyListener;
import cn.javaplus.stock.quotes.CodesUpdate;
import cn.javaplus.stock.quotes.FileSaver;
import cn.javaplus.stock.quotes.QuotesRequester;

public class AppQuotesDownloader {

	public static boolean DEBUG = false;
//	static boolean DEBUG = true;
	
	public static void main(String[] args) {
		CodesUpdate u = new CodesUpdate();
		QuotesRequester r = new QuotesRequester(u);
		FileSaver s = new FileSaver(r);
		s.addListener(new CanBuyListener());
		new Thread(r).start();
		new Thread(s).start();
		new Thread(u).start();
	}
}
