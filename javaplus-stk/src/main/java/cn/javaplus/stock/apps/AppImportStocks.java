package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockNameDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockNameDto;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.util.Util;

public class AppImportStocks {

	public static void main(String[] args) {
		Daos.setProperties(new MongoDbPropertiesImpl());
		importNames();
		importStocks();
	}

	private static void importNames() {
		List<String> ps = Util.File.getLines("stocknames.properties");
		StockNameDao dao = Daos.getStockNameDao();
		for (String kv : ps) {
			String[] split = kv.split("=");
			
			String code =split[0].substring(2, 8);
			StockNameDto dto = dao.createDTO();
			dto.setId(code);
			dto.setName(split[1]);
			dao.save(dto);
			Log.d(dto.getName());
		}
	}

	private static void importStocks() {
		StockReader r = new StockReader();
		It it = new ItImplementation();
		r.foreachShenHu(it);
	}
}
