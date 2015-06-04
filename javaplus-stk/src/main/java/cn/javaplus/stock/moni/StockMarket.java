package cn.javaplus.stock.moni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.stock.apps.MongoDbPropertiesImpl;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class StockMarket {

	public static final int MARKET_RANDOM_STOCK_COUNT = 500;

	private int date;
	private Map<String, Stock2> stocksMap = Maps.newHashMap();

	private static StockMarket instance;

	private List<String> stockIds;

	public static final StockMarket getInstance() {
		if (instance == null)
			instance = new StockMarket();
		return instance;
	}

	@Override
	public String toString() {
		return date + "";
	}

	private StockMarket() {
		Daos.setProperties(new MongoDbPropertiesImpl());
		stockIds = Market.getAllStockIds();
		stockIds = Util.Collection.sub(stockIds, MARKET_RANDOM_STOCK_COUNT);
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getDate() {
		return date;
	}

	/**
	 * 下个交易日
	 */
	public void nextDay() {
		date = Market.nextDate(date);
		if (isSaturday()) {
			date = Market.nextDate(date);
			date = Market.nextDate(date);
		}
	}

	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 是否是星期六
	 */
	private boolean isSaturday() {
		Date dt;
		try {
			dt = sf.parse(date + "");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		int dd = c.get(Calendar.DAY_OF_WEEK);
		return dd == Calendar.SATURDAY;
	}

	public List<Stock2> getStocks() {
		ArrayList<Stock2> ls = Lists.newArrayList();
		for (String id : this.stockIds) {
			ls.add(getStock(id));
		}
		return ls;
	}

	/**
	 * 今日价格
	 * 
	 * @param id
	 * @return
	 */
	public double getPrice(String id) {
		Stock2 stock = stocksMap.get(id);

		DayData data = stock.getByDate(date + "");
		if (data == null || isNoPrice(data))
			data = findDateLowerThanToday(stock);
		return Market.getSellPrice(data);

	}

	/**
	 * 第一个日期小于今日日期的数据
	 */
	private DayData findDateLowerThanToday(Stock2 stock) {
		List<DayData> datas = stock.getDayDatas();
		for (int i = datas.size() - 1; i >= 0; i--) {
			DayData d = datas.get(i);
			if (isNoPrice(d)) {
				continue;
			}
			int date = d.getDateInt();
			if (date < this.date)
				return d;
		}
		return null;
	}

	/**
	 * 这条数据没有价格数据, high == 0
	 * 
	 * @param d
	 * @return
	 */
	private boolean isNoPrice(DayData d) {
		return Math.abs(d.getHigh()) < 0.001;
	}

	public Stock2 getStock(String id) {
		Stock2 stock = stocksMap.get(id);
		if (stock == null) {
			stock = Market.load(id);
			stocksMap.put(id, stock);
		}
		return stock;
	}

	public DayData getToday(Stock2 stock) {
		int date = StockMarket.getInstance().getDate();
		return stock.getByDate(date + "");
	}

}
