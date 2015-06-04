package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.analyze.StockAnalyzer;
import cn.javaplus.stock.analyzers.F;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

public class AppFindStock {

	public static void main(String[] args) throws Exception {

		StockReader r = new StockReader();

		List<Stock1> all = r.readShenHuA();
		// List<Stock> all = Lists.newArrayList(r.read("000895"));

		StockAnalyzer analyzer = new StockAnalyzer();

		AnalyzeStrategy s = new F();

		List<Stock1> stocks = analyzer.analyze(s, all);

		for (Stock1 stock : stocks) {

			System.out.println(stock.getId() + "  -- 收盘日 -- "
					+ stock.getLast().getDate());
		}

	}

}
