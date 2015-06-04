package cn.javaplus.stock.stock;

import java.util.List;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.counter.ICounter;
import cn.javaplus.math.Avg;
import cn.javaplus.stock.apps.Profit;
import cn.javaplus.stock.util.Market;

public class ProfitAnalyzer {

	private double profitEveryDay;
	private ICounter<Integer> counter;
	private double spaceAvg;
	private int count;

	public ProfitAnalyzer(List<Profit> nianHua) {
		Avg profitEveryDay = new Avg();
		Avg spaceAvg = new Avg();

		counter = new Counter<Integer>();
		this.count = nianHua.size();
		for (Profit profit : nianHua) {
			profitEveryDay.add(profit.getProfitEveryDay());
			int spaceDay = profit.getSpaceDay();
			spaceAvg.add(spaceDay);
			counter.add(spaceDay);
		}
		this.spaceAvg = spaceAvg.getValue();
		this.profitEveryDay = profitEveryDay.getValue();
	}

	/**
	 * 每日平均收益
	 */
	public double getProfitEveryDay() {
		return profitEveryDay;
	}

	/**
	 * 键- 持有天数, 值- 随机测试中, 有多少次持有这么多天
	 */
	public ICounter<Integer> getCounter() {
		return counter;
	}

	/**
	 * 统计量(交易次数)
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 平均持有天数
	 */
	public double getSpaceAvg() {
		return spaceAvg;
	}

	/**
	 * 年化收益率(按每年240天计算)
	 */
	public String getProfitYear() {
		double profit = Market.getProfitOneYear(getProfitEveryDay());
		return Market.toPriceString(profit * 100) + "%";
	}


}
