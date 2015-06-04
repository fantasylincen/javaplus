package cn.javaplus.stock.moni;

import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.log.Log;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class Player {

	double rmb;
	List<MyStock> stocks = Lists.newArrayList();

	Map<String, ProfitOfSingleStock> profitOfSingleStock = Maps.newHashMap();


	public void setRmb(double rmb) {
		this.rmb = rmb;
	}

	public double getRmb() {
		return rmb;
	}

	public List<MyStock> getStocks() {
		return stocks;
	}

	/**
	 * @return 总共花费多少钱, 如果失败, 花费为0
	 */
	public double buy(DayData data, int count) {
		return buy(data, count, Market.getBuyPrice(data));
	}

	/**
	 * @return 总共花费多少钱, 如果失败, 花费为0
	 */
	public double buy(DayData data, int count, double singlePrice) {
		if (count == 0)
			return 0;
		double buyPrice = singlePrice * count * 1.00025;
		if (rmb < buyPrice)
			return 0;

		rmb -= buyPrice;
		addStockToMyPacket(data.getId(), singlePrice, count, data);
		return buyPrice;
	}

	void addStockToMyPacket(String id, double singlePrice, int count,
			DayData buyDay) {
		MyStock s = new MyStock();
		s.setId(id);
		s.setSinglePrice(singlePrice);
		s.setCount(count);
		s.setBuyDate(StockMarket.getInstance().getDate());
		s.setBuyDay(buyDay);
		stocks.add(s);

		logBuy(s);
	}

	private void logBuy(MyStock s) {

		Object cnt = Util.Str.fillSpace(s.getCount() + "", 5);
		String sellPrice = Market.toPriceString(s.getSinglePrice());

		DayData buyDay = s.getBuyDay();
		String buyDate = buyDay.getDate();
		double bPrice = s.getSinglePrice();
		String buyPrice = Market.toPriceString(bPrice);

		d("B", "code " + s.getId(), buyDate, buyPrice, "XXXXXXXX", sellPrice,
				cnt, "XXXXXXX", "X", getStocks().size());
	}

	int sellTimes;

	/**
	 * 持仓总价
	 */
	double getStocksValue() {
		double sum = 0;
		for (MyStock s : stocks) {
			sum += s.getCurrentPriceAll();
		}
		return sum;
	}

	public void sell(MyStock s) {
		double all = s.getCurrentPriceAll();
		sell(s, all);
	}

	public void sell(MyStock s, double all) {
		stocks.remove(s);
		rmb += all;
		sellTimes++;
		log(s, all);
	}

	@SuppressWarnings("unused")
	void log(MyStock s, double all) {
		double singleSellPrice = all / s.getCount();

		Object cnt = Util.Str.fillSpace(s.getCount() + "", 5);
		String sellPrice = Market.toPriceString(singleSellPrice);
		DayData today = s.getToday();

		String sellDate = today.getDate();

		DayData buyDay = s.getBuyDay();
		String buyDate = buyDay.getDate();
		double bPrice = s.getSinglePrice();
		String buyPrice = Market.toPriceString(bPrice);

		int space = Market.getSpace(buyDate, sellDate);

		double p = (singleSellPrice - bPrice) / bPrice;
		String profit = Market.toPriceString(p * 100) + "%";

		saveProfitOfSingleStock(s.getId(), p,
				(singleSellPrice - bPrice) * s.getCount());

		d("S", "code " + s.getId(), buyDate, buyPrice, sellDate, sellPrice,
				cnt, profit, space, getStocks().size());
	}

	public Map<String, ProfitOfSingleStock> getProfitOfSingleStock() {
		return profitOfSingleStock;
	}

	void saveProfitOfSingleStock(String id, double profitPercent,
			double profitRmb) {
		ProfitOfSingleStock ss = profitOfSingleStock.get(id);
		if (ss == null) {
			ss = new ProfitOfSingleStock(id);
			profitOfSingleStock.put(id, ss);
		}

		ss.add(profitPercent, profitRmb);

	}

	void d(Object... objs) {
		Log.d(objs);
	}

	public int getSellTimes() {
		return sellTimes;
	}

	/**
	 * 获取指定ID的持仓金额
	 */
	public double getAsset(String id) {
		double sum = 0;
		for (MyStock s : stocks) {
			if (s.getId().equals(id)) {
				sum += s.getCurrentPriceAll();
			}
		}
		return sum;
	}

	public double getBuyUnit() {
		double all = getAll();
		return all / 5;
	}

	/**
	 * 所有股票大概值多少钱
	 */
	public double getAll() {
		double all = getRmb();
		List<MyStock> a = getStocks();
		for (MyStock m : a) {
			double p = m.getSinglePrice();
			int count = m.getCount();
			all += count * p;
		}
		return all;
	}


}
