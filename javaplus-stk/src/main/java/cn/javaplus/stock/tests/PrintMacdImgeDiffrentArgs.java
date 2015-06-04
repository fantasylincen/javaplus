package cn.javaplus.stock.tests;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import cn.javaplus.stock.apps.AppImportStocks;
import cn.javaplus.stock.apps.AppPrintBuyStratege4;
import cn.javaplus.stock.apps.ItImplementation;
import cn.javaplus.stock.apps.MongoDbPropertiesImpl;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDto;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.util.Util;

public class PrintMacdImgeDiffrentArgs {
	
	public static class TestIt extends ItImplementation implements It{

		int count = 100;
		
		@Override
		public void onRead(Stock1 read) {
			if(count -- < 0)
				return;
			super.onRead(read);
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		int normalLong = 26;
		int normalShort = 12;
		int normalMid = 9;

		PrintStream out = System.out;
		
		System.setOut(new PrintStream("xx.csv"));
		for (int macdLong = normalLong - 5; macdLong <= normalLong + 5; macdLong++) {
			for (int macdShort = normalShort - 5; macdShort <= normalShort + 5; macdShort++) {
				for (int macdMid = normalMid - 5; macdMid <= normalMid + 5; macdMid++) {
					run(macdLong, macdShort, macdMid);
					out.println(macdLong + ", " + macdShort + ", " + macdMid);
				}
			}
		}
	}

	private static void run(int macdLong, int macdShort, int macdMid)
			throws FileNotFoundException {

		Stock1.MACD_LONG = macdLong;
		Stock1.MACD_SHORT = macdShort;
		Stock1.MACD_MID = macdMid;
		Daos.setProperties(new MongoDbPropertiesImpl());

		Daos.getStockDao().clear();
		Daos.getStockIdDao().clear();
		
		StockReader r = new StockReader();
		List<String> ids = r.getShenHuIds();
		Util.Collection.upset(ids);
		ids = Util.Collection.sub(ids, 100);
		
		StockIdDao dao = Daos.getStockIdDao();
		for (String id : ids) {
			StockIdDto dto = dao.createDTO();
			dto.setId(id);
			dao.save(dto);
		}
		
		It it = new TestIt();
		r.foreach(it, ids);

		AppPrintBuyStratege4.main(null);
	}
}
