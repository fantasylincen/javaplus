package cn.javaplus.stock.apps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDto;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class AppImportStockPrices {

	private static final String A = "C:\\Users\\Administrator\\Desktop\\股票五分钟2008csv\\股票五分钟2008csv\\";
	private static Set<String> ids = Sets.newHashSet();

	public static void main(String[] args) {
		Daos.setProperties(new MongoDbPropertiesImpl());

		ArrayList<String> idss = Market.getAllStockIds();

		ids.addAll(idss);

		File file = new File(A);
		File[] all = file.listFiles();
		for (File f : all) {
			importF(f);
		}
	}

	private static void importF(File f) {
		List<String> lines = Util.File.getLines(f);

		String name = f.getName();
		name = name.substring(2, 8);

		if (!ids.contains(name))
			return;
		Log.d(name);

		StockPriceDao dao = Daos.getStockPriceDao();

		for (String line : lines) {
			String[] s = line.split(",");
			String dt = s[0].replaceAll("/", "");
			String c = s[1];
			String price = s[2];

			StockPriceDto dto = dao.createDTO();
			dto.setId(name);
			dto.setTime(dt + c.replace(":", ""));
			dto.setPrice(price);

			dao.save(dto);
		}

		Log.d("import", name);

	}
}
