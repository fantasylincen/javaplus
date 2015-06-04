package cn.javaplus.stock.moni;

import cn.javaplus.stock.stock.DayData;

public class TradeLog {

	int date;
	boolean isBuy;
	String stockId;
	int count;
	String asset;
	String price;
	private double rmbRemain;
	private DayData sellDay;
	private DayData buyDay;
	private double rmbProfit;
	private double profitPercent;

	public int getDate() {
		return date;
	}

	public double getRmbProfit() {
		return rmbProfit;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setRmbRemain(double rmbRemain) {
		this.rmbRemain = rmbRemain;
	}

	public double getRmbRemain() {
		return rmbRemain;
	}

	public DayData getBuyDay() {
		return buyDay;
	}

	public void setBuyDay(DayData buyDay) {
		this.buyDay = buyDay;
	}

	public DayData getSellDay() {
		return sellDay;
	}

	public void setSellDay(DayData sellDay) {
		this.sellDay = sellDay;
	}

	public void setRmbProfit(double rmbProfit) {
		this.rmbProfit = rmbProfit;
	}

	public void setProfitPercent(double profitPercent) {
		this.profitPercent = profitPercent;
	}

	public double getProfitPercent() {
		return profitPercent;
	}
}
