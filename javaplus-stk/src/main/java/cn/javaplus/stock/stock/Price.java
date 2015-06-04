package cn.javaplus.stock.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.math.Avg;
import cn.javaplus.time.Time;

public class Price {

	private int date;

	private double open;

	private double high;

	private double lower;

	private double close;

	private int volume;

	private Price yestoday;

	static SimpleDateFormat SF = new SimpleDateFormat("yyyyMMdd");

	private Price tomorrow;

	private String id;

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
	public int getVolume() {
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

	public void setVolume(int volume) {
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

	public void setYestoday(Price yestoday) {
		this.yestoday = yestoday;
	}

	public Price getYestoday() {
		return yestoday;
	}

	@Override
	public String toString() {
		return "" + getDate() + " - " + getOpen() + " - " + getClose() + " - "
				+ getUp() * 100 + "%";
	}

	public boolean hasChuQuan() {

		double o = getOpen();
		Price yestoday = getYestoday();
		if (yestoday == null)
			return false;
		double c = yestoday.getClose();

		return (o - c) / c < -0.12;
	}

	public void setTomorrow(Price tomorrow) {
		this.tomorrow = tomorrow;
	}

	public Price getTomorrow() {
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
	public Price getHighestPrice(int day) {
		Price t = this;
		double max = t.getHigh();
		Price maxPrice = t;
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
		
		Price t = this;
		Avg avg = new Avg();
		for (int i = 0; i < day; i++) {
			t = t.getYestoday();
			int v = t.getVolume();
			avg.add(v);
		}
		return avg.getValue();
	}

}
