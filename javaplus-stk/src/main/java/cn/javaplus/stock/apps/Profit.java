package cn.javaplus.stock.apps;

public class Profit {

	private int spaceDay;
	private double profit;

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void setSpaceDay(int spaceDay) {
		this.spaceDay = spaceDay;
	}

	public double getProfit() {
		return profit;
	}

	public int getSpaceDay() {
		return spaceDay;
	}

	/**
	 * 平均日收益
	 */
	public double getProfitEveryDay() {
		return profit / spaceDay;
	}
}
