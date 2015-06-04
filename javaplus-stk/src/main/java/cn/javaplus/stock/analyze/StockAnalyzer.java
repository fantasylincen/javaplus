package cn.javaplus.stock.analyze;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.stock.statistics.Foreach;
import cn.javaplus.stock.stock.Stock1;

import com.google.common.collect.Lists;

public class StockAnalyzer {

	/**
	 * 分析
	 * @param as 分析策略
	 * @param all 所有股票
	 */
	public List<Stock1> analyze(AnalyzeStrategy as, List<Stock1> all) {
		ArrayList<Stock1> ls = Lists.newArrayList();
		for (Stock1 stock : all) {
			if(as.conform(stock) ) {
				ls.add(stock);
			}
		}
		return ls;
	}


}
