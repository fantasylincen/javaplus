package cn.javaplus.stock.analyze;

import cn.javaplus.stock.stock.Stock1;

/**
 * 分析策略
 */
public interface AnalyzeStrategy {

	/**
	 * 是否符合条件
	 */
	boolean conform(Stock1 stock);

}
