package cn.javaplus.stock.tests;

import java.util.List;

import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

public class TestMACD {
	public static void main(String[] args) {
		StockReader r = new StockReader();
		Stock1 s = r.read("002241");
		List<OneDayData> datas = s.getDayDatas();
		
		for (OneDayData o : datas) {
			System.out.println(o.getDate() + " " + graphics(o));
		}
	}

	private static String graphics(OneDayData o) {
		StringBuilder sb = new StringBuilder();
		double macd = o.getMacd();
		for (double d = -3; d < 3; d += 0.04) {
			if (macd < 0) {
				if (macd < d && d < 0) {
					sb.append("|");
				} else {
					sb.append("_");
				}
			} else {
				if (macd > d && d > 0) {
					sb.append("|");
				} else {
					sb.append("_");
				}
			}
		}
		sb.append("   " + macd);
		return sb.toString();
	}
}
