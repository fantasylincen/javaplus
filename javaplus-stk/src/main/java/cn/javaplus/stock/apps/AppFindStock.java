package cn.javaplus.stock.apps;

import cn.javaplus.stock.analyze.AnalyzeStrategy;
import cn.javaplus.stock.analyzers.G;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

public class AppFindStock {

	private static final class ItImplementation2 implements It {
		@Override
		public void onRead(Stock1 stock) {

			AnalyzeStrategy s = new G();

			if (s.conform(stock)) {
				System.out.println(stock.getId() + "  -- 收盘日 -- " + stock.getLast().getDate());
			}

		}
	}

	public static void main(String[] args) throws Exception {
		StockReader r = new StockReader();
		It it = new ItImplementation2();
		r.foreachShenHu(it);
	}

}
