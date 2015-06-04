package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.stock.moni.FenShiDownloader;
import cn.javaplus.stock.moni.FenShiMingXi;
import cn.javaplus.stock.moni.StockMarket;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class AppDownloadFenShiJiaoYiMingXi {

	public static void main(String[] args) {
		StockMarket ins = StockMarket.getInstance();

		List<String> ids = Lists.newArrayList(Market.getAllStockIds());
		Util.Collection.upset(ids);
		for (String id : ids) {
			Stock2 s = ins.getStock(id);
			List<DayData> dayDatas = s.getDayDatas();
			for (DayData d : dayDatas) {
				String date = d.getDate();
				try {
					new FenShiDownloader().download(id, date, FenShiMingXi.DIR);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
