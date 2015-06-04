package cn.javaplus.stock.fastbuy;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

public class BuyTingPais {
	public static void main(String[] args) {
		List<Stock1> all = new StockReader().readShenHuA();
		for (Stock1 stock : all) {
			OneDayData last = stock.getLast();
			if (last == null)
				continue;

			long dateMillis = last.getDateMillis();

			boolean hasTingPai30Day = System.currentTimeMillis() - dateMillis > Time.MILES_ONE_DAY * 30;

			if (hasTingPai30Day) {
				String id = stock.getId();
				if (last.getDate() < 20140901) {
					continue;
				}
				if (id.startsWith("300")) {
					continue;
				}
				if(last.getClose() > 35)
					continue;
				String price = toPriceString(last.getClose());
				String buyPrice = toPriceString(new Double(price) * 1.1);
				Log.d("停牌30天以上的股票:" + id + " 时间:" + last.getDate() + "  价格:"
						+ price + "    建议价格:" + buyPrice);
				 new TongHuaShunRobot().buy(id, buyPrice, "100");
				 Util.Thread.sleep(1000);
			}
		}
	}

	/**
	 * 保留2位小数 四舍五入
	 */
	private static String toPriceString(double close) {
		return String.format("%.2f", close);
	}

}
