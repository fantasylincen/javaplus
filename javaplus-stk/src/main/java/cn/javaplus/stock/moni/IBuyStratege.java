package cn.javaplus.stock.moni;

import java.util.List;

import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;

public interface IBuyStratege {

	/**
	 * 找出最优的, 并由可买优先级 按先后顺序排序
	 */
	List<IStockRecommend> findBestSorted(List<Stock2> stocks);

	/**
	 * 把该卖的卖了
	 * 
	 * @param player
	 */
	void sell(Player player);

	/**
	 * 计算本次该股最优购买数量
	 * 
	 * @param player
	 * @param data
	 * @return
	 */
	int getBuyCountThisTime(Player player, DayData data);

}
