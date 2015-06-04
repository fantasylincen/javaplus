package cn.javaplus.stock.stock;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

import cn.javaplus.math.Avg;
import cn.javaplus.time.Time;

public class OneDayData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5519258645230932577L;

	private int date;

	private double open;

	private double high;

	private double lower;

	private double close;

	private long volume;

	private OneDayData yestoday;

	static SimpleDateFormat SF = new SimpleDateFormat("yyyyMMdd");

	private OneDayData tomorrow;

	private String id;

	private double macd;

	private Map<Integer, Double> emas = Maps.newHashMap();

	private double diff;

	private double dea;

	private double percentOfFuQuan;

	/**
	 * 日期
	 */
	public int getDate() {
		return date;
	}

	public long getDateMillis() {
		Date date;
		try {
			date = SF.parse(getDate() + "");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date.getTime();
	}

	/**
	 * 开盘
	 */
	public double getOpen() {
		return open;
	}

	/**
	 * 最高
	 */
	public double getHigh() {
		return high;
	}

	/**
	 * 最低
	 */
	public double getLow() {
		return lower;
	}

	/**
	 * 收盘
	 */
	public double getClose() {
		return close;
	}

	/**
	 * 成交量
	 */
	public long getVolume() {
		return volume;
	}

	public void setDate(int date) {
		if (date < 10000000)
			throw new RuntimeException(date + "");
		this.date = date;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setLow(double lower) {
		this.lower = lower;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	/**
	 * 涨幅
	 */
	public double getUp() {
		if (yestoday == null)
			return 0;
		double c = yestoday.getClose();

		return (getClose() - c) / c;
	}

	/**
	 * 是否是最近几天
	 */
	public boolean isNear() {
		int date = getDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = sf.parse(date + "");
			return Math.abs((d.getTime() - System.currentTimeMillis())
					/ Time.MILES_ONE_DAY) <= 5;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	public void setYestoday(OneDayData yestoday) {
		this.yestoday = yestoday;
	}

	public OneDayData getYestoday() {
		return yestoday;
	}

	@Override
	public String toString() {
		return "" + getDate() + " - " + getOpen() + " - " + getClose() + " - "
				+ getUp() * 100 + "%";
	}

	public boolean hasChuQuan() {

		double o = getOpen();
		OneDayData yestoday = getYestoday();
		if (yestoday == null)
			return false;
		double c = yestoday.getClose();

		return (o - c) / c < -0.12;
	}

	public void setTomorrow(OneDayData tomorrow) {
		this.tomorrow = tomorrow;
	}

	public OneDayData getTomorrow() {
		return tomorrow;
	}

	/**
	 * getOpen() / 2 + getClose() / 2
	 */
	public double getAvg() {
		return getOpen() / 2 + getClose() / 2;
	}

	/**
	 * day ri nei zui gao jiage
	 */
	public OneDayData getHighestPrice(int day) {
		OneDayData t = this;
		double max = t.getHigh();
		OneDayData maxPrice = t;
		for (int i = 0; i < day; i++) {
			t = t.getYestoday();
			if (t == null)
				break;
			if (t.getHigh() > max) {
				max = t.getHigh();
				maxPrice = t;
			}
		}
		return maxPrice;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	/**
	 * 今天之前的成交量均值
	 */
	public double getAvgChengJiaoLiang(double day) {

		OneDayData t = this;
		Avg avg = new Avg();
		for (int i = 0; i < day; i++) {
			t = t.getYestoday();
			long v = t.getVolume();
			avg.add(v);
		}
		return avg.getValue();
	}

	public void setMacd(double macd) {
		this.macd = macd;
	}

	public double getMacd() {
		return macd;
	}

	public double getEma(int day) {
		try {
			return emas.get(day);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setEma(int day, double ema) {
		this.emas.put(day, ema);
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public double getDiff() {
		return diff;
	}

	public void setDea(double dea) {
		this.dea = dea;
	}

	public double getDea() {
		return dea;
	}

	/**
	 * 前N日 open close 均价(包括今日) 比如day = 2, 表示今天和昨天的均价
	 * 
	 * @param day
	 * @return
	 */
	public double getAvg(int day) {
		Avg avg = new Avg();
		OneDayData p = this;
		for (int i = 0; i < day; i++) {
			avg.add(p.getAvg());
			p = p.getYestoday();
			if (p == null)
				break;
		}
		return avg.getValue();
	}

	/**
	 * day 日内最高价格
	 * 
	 * @param day
	 * @return
	 */
	public double getMax(int day) {
		OneDayData p = this;
		double max = p.getHigh();
		for (int i = 0; i < day; i++) {

			double h = p.getHigh();
			if (h > max)
				max = h;

			p = p.getYestoday();
			if (p == null)
				break;
		}
		return max;
	}

	public double getOpenCloseZhenFu() {
		double ck = getCanKaoJia();
		double o = getOpen();
		double c = getClose();
		return Math.abs((c - o) / ck);
	}

	public double getHighLowZhenFu() {
		double ck = getCanKaoJia();
		double h = getHigh();
		double l = getLow();
		return Math.abs((l - h) / ck);
	}

	public double getPercentOfFuQuan() {
		return percentOfFuQuan;
	}

	private double getCanKaoJia() {
		OneDayData y = getYestoday();
		if (y == null)
			return getOpen();
		return y.getClose();
	}

	public void setPercentOfFuQuan(double percentOfFuQuan) {
		this.percentOfFuQuan = percentOfFuQuan;
		
	}

}
