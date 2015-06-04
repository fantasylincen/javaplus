package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.log.Log;
import cn.javaplus.stock.fastbuy.Refresher;
import cn.javaplus.stock.fastbuy.TongHuaShunRobot;
import cn.javaplus.util.Util;

public class AppBuyStockOn915 {

	static boolean isDebug = false;
//	static boolean isDebug = true;

	static SimpleDateFormat sf = new SimpleDateFormat("HHmmss");

	public static class BuyThread extends Thread {
		@Override
		public void run() {

			String buyTime = "091456";

			if (isDebug)
				buyTime = getTimeDebug();

			while (true) {
				String now = sf.format(new Date(System.currentTimeMillis()));

				if (now.equals(buyTime)) {
					// 002509

					String code1 = "000415"; 
					String price1 = "15.95";
					String count1 = "200";

//					String code2 = "000616";// 海航投资
//					String price2 = "5.78";
//					String count2 = "200";
					
					for (int i = 0; i < 7; i++) {
						buy(code1, price1, count1);
						Util.Thread.sleep(1000);
					}

					Log.d("over");
					break;
				}
				Util.Thread.sleep(100);
			}
			System.exit(0);
		}

		private void buy(String code, String price, String count) {
			new TongHuaShunRobot().buy(code, price, count);
		}

		private String getTimeDebug() {
			String t = sf.format(new Date(System.currentTimeMillis() + 1000));
			return t;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new BuyThread().start();
		new Refresher().start();
	}

}
