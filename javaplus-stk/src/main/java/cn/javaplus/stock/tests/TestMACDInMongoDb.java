package cn.javaplus.stock.tests;

import java.util.List;

import cn.javaplus.stock.apps.MongoDbPropertiesImpl;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockDto;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;

public class TestMACDInMongoDb {
	public static void main(String[] args) {
		Daos.setProperties(new MongoDbPropertiesImpl());
		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get("002241");

		Stock2 stock = new Stock2(dto);

		List<DayData> datas = stock.getDayDatas();
		for (DayData d : datas) {
			System.out.println(d.getDate() + " " + graphics(d));
		}

	}

	private static String graphics(DayData d2) {
		StringBuilder sb = new StringBuilder();
		double macd = d2.getMacd();
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
