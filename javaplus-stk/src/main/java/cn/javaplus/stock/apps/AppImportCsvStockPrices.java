package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDto;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

/**
 * 导入csv中的股票价格数据
 */
public class AppImportCsvStockPrices {

	public static void main(String[] args) throws FileNotFoundException {
		Daos.setProperties(new MongoDbPropertiesImpl());
		StockPriceDao dao = Daos.getStockPriceDao();

		Set<String> all = Market.getSelectStocks();
		for (String code : all) {
			String fileName = "G:/来自标普永华/5min2008Parse/"
					+ Market.getCode(code).toUpperCase() + ".csv";
			List<String> lines = Util.File.getLines(fileName);
			for (String line : lines) {
				String[] ss = line.split(",");

				String date = ss[0];
				String clock = ss[1];
				String price = ss[2];
				String closeYes = ss[3];
				String high = ss[4];
				String low = ss[5];

				StockPriceDto p = dao.createDTO();
				p.setId(code);
				p.setPrice(price);
				p.setTime(date + ":" + clock);
				p.setCloseYestoday(closeYes);
				p.setHighToday(high);
				p.setLowToday(low);

				dao.save(p);
			}
			Log.d("load over", code);
		}
	}

}
