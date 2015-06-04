package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;

import cn.javaplus.stock.fastbuy.Buyer;
import cn.javaplus.stock.fastbuy.GpCouldBuyMonitor;
import cn.javaplus.stock.fastbuy.Refresher;

public class AppFastBuy {

	public static void main(String[] args) throws FileNotFoundException {
		
//		System.setOut(new PrintStream(new File("out.txt")));
//		System.setErr(new PrintStream(new File("err.txt")));
		
		
		GpCouldBuyMonitor monitor = new GpCouldBuyMonitor(); //用于从新浪拉取可以购买的股票ID
		monitor.start();
		
		new Buyer(monitor.getGpCouldBuy()).start();
		new Refresher().start();
	}

}
