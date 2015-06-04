package cn.javaplus.stock.stock;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

public class Stock1 implements Serializable {

	public static int MACD_MID = 9;

	public static int MACD_LONG = 26;

	public static int MACD_SHORT = 12;

	/**
	 * 
	 */
	private static final long serialVersionUID = 65615999483984913L;

	private List<OneDayData> dayDatas = Lists.newArrayList();
	private String id;

	public Stock1(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<OneDayData> getDayDatas(String start, String end) {
		ArrayList<OneDayData> ls = Lists.newArrayList();
		for (OneDayData o : dayDatas) {
			int date = o.getDate();
			if (new Integer(start) <= date && date <= new Integer(end)) {
				ls.add(o);
			}
		}
		return ls;
	}

	public List<OneDayData> getDayDatas() {
		return dayDatas;
	}

	public void add(OneDayData data) {
		this.dayDatas.add(data);
	}

	/**
	 * 获取指定时间段的 开盘价和收盘价的平均值
	 * 
	 * @param from
	 *            开始时间: yyyyMMdd
	 * @param to
	 *            结束时间: yyyyMMdd
	 */
	public double getAvg(String from, String to) {
		double allOpen = 0;
		double allClose = 0;
		int count = 0;

		for (OneDayData data : dayDatas) {
			if (isIn(from, to, data)) {
				allOpen += data.getOpen();
				allClose += data.getClose();
				count++;
			}
		}
		return (allOpen + allClose) / count / 2;
	}

	private boolean isIn(String from, String to, OneDayData data) {
		String date = data.getDate() + "";

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date f = sf.parse(from);
			Date t = sf.parse(to);
			Date d = sf.parse(date);
			int c1 = d.compareTo(t); // less than 0 if this Date is before
			int c2 = d.compareTo(f);

			return c1 <= 0 && c2 >= 0;

		} catch (ParseException e) {
			Log.e("出错代码:" + getId());
			throw new RuntimeException(e);
		}

	}

	/**
	 * @return 最后一天的价格
	 */
	public OneDayData getLast() {
		if (dayDatas.isEmpty())
			return null;
		return dayDatas.get(dayDatas.size() - 1);
	}

	/**
	 * 在指定周期内, 是否有过除权
	 * 
	 * @param from
	 *            ("yyyyMMdd");
	 * @param to
	 *            ("yyyyMMdd");
	 * @return
	 */
	public boolean hasChuQuan(String from, String to) {
		ArrayList<OneDayData> ls = Lists.newArrayList();
		for (OneDayData data : dayDatas) {
			if (isIn(from, to, data)) {
				ls.add(data);
			}
		}

		for (int i = 0; i < ls.size() - 1; i++) {
			OneDayData a = ls.get(i);
			OneDayData b = ls.get(i + 1);

			double close = a.getClose();
			double open = b.getOpen();

			if (Math.abs((open - close) / close) > 0.12) {
				return true;
			}

		}

		return false;

	}

	/**
	 * 复权
	 */
	void fuQuan() {

		List<OneDayData> ls = Lists.newArrayList(dayDatas);
		Collections.reverse(ls);
		while (true) {
			int index = findTooLowIndex(ls);
			if (index == -1)
				break;
			fuQuanFrom(index, ls);// 从位置index开始复权
		}
	}

	private int findTooLowIndex(List<OneDayData> ls) {
		for (int i = 0; i < ls.size(); i++) {
			OneDayData data = ls.get(i);

			if (data.hasChuQuan())
				return i;
		}
		return -1;
	}

	private void fuQuanFrom(int index, List<OneDayData> ls) {
		OneDayData data = ls.get(index);
		OneDayData next = ls.get(index + 1);

		double percent = data.getOpen() / next.getClose();

		for (int i = index + 1; i < ls.size(); i++) {
			OneDayData p = ls.get(i);
			p.setClose(p.getClose() * percent);
			p.setHigh(p.getHigh() * percent);
			p.setLow(p.getLow() * percent);
			p.setOpen(p.getOpen() * percent);
			p.setPercentOfFuQuan(percent);
		}
	}

	/**
	 * day 个交易日的均价(收盘开盘均价)
	 * 
	 * @param day
	 * @return
	 */
	public double getAvg(int day) {
		double allOpen = 0;
		double allClose = 0;

		List<OneDayData> ps = getLast(day);
		for (OneDayData data : ps) {
			allOpen += data.getOpen();
			allClose += data.getClose();
		}
		return (allOpen + allClose) / ps.size() / 2;
	}

	/**
	 * 最后day个交易日的价格
	 * 
	 * @param day
	 * @return
	 */
	private List<OneDayData> getLast(int day) {
		List<OneDayData> ps = Lists.newArrayList(dayDatas);
		Collections.reverse(ps);
		ps = Util.Collection.sub(ps, day);
		Collections.reverse(ps);
		return ps;
	}

	/**
	 * day 个交易日的 振幅平均值
	 * 
	 * @param day
	 * @return
	 */
	public double getUpAvg(int day) {
		double all = 0;

		List<OneDayData> ps = getLast(day);
		for (OneDayData data : ps) {
			all += Math.abs(data.getUp());
		}
		return (all) / ps.size();
	}

	/**
	 * 最后day日最高价
	 * 
	 * @param day
	 * @return
	 */
	public double getHighest(int day) {
		double max = 0;

		List<OneDayData> ps = getLast(day);
		for (OneDayData data : ps) {
			if (data.getHigh() > max)
				max = data.getHigh();
		}
		return max;
	}

	public List<TwoDaysData> getTwoDaysData() {
		ArrayList<TwoDaysData> ls = Lists.newArrayList();
		List<OneDayData> ps = getDayDatas();
		for (int i = 0; i < ps.size() - 1; i++) {
			OneDayData before = ps.get(i);
			OneDayData after = ps.get(i + 1);

			ls.add(new TwoDaysData(before, after));
		}
		return ls;
	}

	public List<OneDayData> getDayDatas(String year) {
		ArrayList<OneDayData> ls = Lists.newArrayList();
		for (OneDayData data : getDayDatas()) {
			String date = data.getDate() + "";
			if (date.startsWith(year))
				ls.add(data);
		}
		return ls;
	}

	/**
	 * 计算各项指标
	 */
	public void calcZhiBiao() {
		macd2();
	}

	private void calcDea() {

		int day = MACD_MID;

		List<Double> list = Lists.newArrayList();
		for (OneDayData o : dayDatas) {
			list.add(o.getDiff());
		}

		Double k = 2.0 / (day + 1.0);// 计算出序数
		Double dea = list.get(0);// 第一天ema等于当天收盘价
		dayDatas.get(0).setDea(0);
		for (int i = 1; i < list.size(); i++) {
			// 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
			dea = list.get(i) * k + dea * (1 - k);
			dayDatas.get(i).setDea(dea);
		}

	}

	private void calcDiff() {

		for (OneDayData o : dayDatas) {
			double emaShort = o.getEma(MACD_SHORT);
			double emaLong = o.getEma(MACD_LONG);
			double diff = emaShort - emaLong;
			o.setDiff(diff);
		}
	}

	private void macd2() {

		calcEma(MACD_SHORT);
		calcEma(MACD_LONG);
		calcDiff();
		calcDea();

		for (OneDayData o : dayDatas) {
			double dif = o.getDiff();
			double dea = o.getDea();
			double macd = (dif - dea) * 2;
			o.setMacd(macd);
		}
	}

	/**
	 * Calculate EMA,
	 * 
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @return
	 */
	public static final Double getEXPMA(final List<Double> list,
			final int number) {
		// 开始计算EMA值，
		Double k = 2.0 / (number + 1.0);// 计算出序数
		Double ema = list.get(0);// 第一天ema等于当天收盘价

		for (int i = 1; i < list.size(); i++) {
			// 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
			ema = list.get(i) * k + ema * (1 - k);
		}
		return ema;
	}

	private void calcEma(int day) {

		List<Double> list = Lists.newArrayList();
		for (OneDayData o : dayDatas) {
			list.add(o.getClose());
		}

		Double k = 2.0 / (day + 1.0);// 计算出序数
		Double ema = list.get(0);// 第一天ema等于当天收盘价
		dayDatas.get(0).setEma(day, 0);
		for (int i = 1; i < list.size(); i++) {
			// 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
			ema = list.get(i) * k + ema * (1 - k);
			dayDatas.get(i).setEma(day, ema);
		}
	}

	/**
	 * calculate MACD values
	 * 
	 * @param list
	 *            :Price list to calculate，the first at head, the last at tail.
	 * @param shortPeriod
	 *            :the short period value.
	 * @param longPeriod
	 *            :the long period value.
	 * @param midPeriod
	 * @return
	 */
	public static final List<Double> calcMacd(final List<Double> list,
			final int shortPeriod, final int longPeriod, int midPeriod) {
		List<Double> diffList = new ArrayList<Double>();
		Double shortEMA = 0.0;
		Double longEMA = 0.0;
		Double dif = 0.0;
		Double dea = 0.0;

		long t = System.currentTimeMillis();

		ArrayList<Double> ls = Lists.newArrayList();
		for (int i = list.size() - 1; i >= 0; i--) {
			List<Double> sublist = list.subList(0, list.size() - i);
			shortEMA = getEXPMA(sublist, shortPeriod);
			longEMA = getEXPMA(sublist, longPeriod);
			dif = shortEMA - longEMA;
			diffList.add(dif);
			dea = getEXPMA(diffList, midPeriod);
			double macd = (dif - dea) * 2;
			ls.add(macd);
			// Log.d(dea, dif);
		}
		Log.d(System.currentTimeMillis() - t);
		return ls;
	}

}
