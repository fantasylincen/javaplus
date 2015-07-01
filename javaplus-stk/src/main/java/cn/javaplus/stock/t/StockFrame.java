package cn.javaplus.stock.t;

import java.util.List;

import cn.javaplus.stock.quotes.StockRecord;
import cn.javaplus.stock.stock.Price;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;

import com.google.common.collect.Lists;

public class StockFrame extends BufferedFrame {

	public class StockThread extends Thread {
		@Override
		public void run() {
			while (true) {
				if (Market.inJiaoYiTime()) {
					update();
				}
				Util.Thread.sleep(1000);
			}
		}

	}
	
	List<IPrice> prices = Lists.newArrayList();
	
	private void update() {
		String url = "http://hq.sinajs.cn/list=sh600959";
		String content = WebContentFethcer.get("gb2312", url);
		StockRecord r = new StockRecord(content);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7674879173255116805L;

	public StockFrame() {
		new StockThread().start();
	}

}
