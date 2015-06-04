package cn.javaplus.stock.moni;

import cn.javaplus.math.Avg;
import cn.javaplus.stock.apps.Profit;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.util.Market;

public class NianHuaCalc {
	
	Avg profit = new Avg();

	public void save(DayData buyDay, DayData sellDay) {
		double buyPrice = Market.getBuyPrice(buyDay);
		double sellPrice = Market.getSellPrice(sellDay);

		double profit = (sellPrice - buyPrice) / buyPrice;

		int spaceDay = Market.getSpaceDay(sellDay, buyDay);

		Profit p = new Profit();
		p.setProfit(profit);
		p.setSpaceDay(spaceDay);

		double profitEveryDay = p.getProfitEveryDay();
		this.profit.add(profitEveryDay);
	}

	/**
	 * 平均年收益率
	 */
	public double getNianHua() {
		double value = profit.getValue();
		double profitOneYear = Market.getProfitOneYear(value);
		return profitOneYear;
	}
}
