package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.List;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

/**
 * tong ji macd
 */
public class AppTongJiMacd {

	public static void main(String[] args) throws FileNotFoundException {
		StockReader r = new StockReader();
		List<Stock1> all = r.readShenHuA();

		Counter<String> counter = new Counter<>();
		for (Stock1 stock : all) {
			List<OneDayData> datas = stock.getDayDatas();
			for (OneDayData o : datas) {
				double macd = o.getMacd();
				if (macd > 0)
					counter.add("+");
				else
					counter.add("-");
			}
		}
		System.out.println(counter);
	}

}
