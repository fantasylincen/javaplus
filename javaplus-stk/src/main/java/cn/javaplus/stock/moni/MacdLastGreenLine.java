package cn.javaplus.stock.moni;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.math.Avg;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.util.ImageSaver;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class MacdLastGreenLine {

	private int loseTimes;
	private int winTimes;
	private double winX = 1;
	private double loseX = 1;
	private int count;
	private Avg winXAvg = new Avg();
	private Avg loseXAvg = new Avg();
	private double profitAll = 1;

	public void process(Stock2 stc) {
		List<DayData> all = stc.getDayDatas();
		List<DayData> finds = findLastGreenMacd(all);
		for (DayData dt : finds) {
			if (dt.getTommorrow() == null)
				continue;

			double winPercent = getWinPercent(dt);

			if (Util.Random.isHappen(0.01))
				new ImageSaver().saveToImage(dt, dt.getTommorrow());
			if (winPercent > 1) {
				winTimes++;
				winX *= winPercent;
				winXAvg.add(winPercent);
			} else {
				loseTimes++;
				loseX *= winPercent;
				loseXAvg.add(winPercent);
			}
			count++;
			profitAll *= winPercent;
		}
	}

	public Avg getLoseXAvg() {
		return loseXAvg;
	}

	public Avg getWinXAvg() {
		return winXAvg;
	}

	public int getCount() {
		return count;
	}

	public double getProfitAll() {
		return profitAll;
	}

	public double getWinX() {
		return winX;
	}

	public double getLoseX() {
		return loseX;
	}

	private double getWinPercent(DayData dt) {
		double close = dt.getClose();
		DayData t = dt.getTommorrow();
		double cls = t.getClose();
		return cls / close;
	}

	private List<DayData> findLastGreenMacd(List<DayData> all) {
		ArrayList<DayData> ls = Lists.newArrayList();
		for (DayData dt : all) {

			double macd = dt.getMacd();
			boolean a = -getMaxMacdAbs(dt) / 10 < macd && macd < 0
					&& dt.getMacdGreenCountBefore() >= 5;
			boolean b = zhenFuLow(dt);

			boolean c = c(dt);
			if (a && b && c)
				ls.add(dt);
		}
		return ls;
	}

	/**
	 * 当日成交量 < 50日内最高成交量 * 0.2
	 * 
	 * @param i
	 * @param dt
	 * @return
	 */
	private boolean c(DayData dt) {
		DayData now = dt;
		long max = dt.getVolume();
		for (int j = 0; j < 50; j++) {
			dt = dt.getYestoday();
			if (dt == null)
				break;

			long m = dt.getVolume();
			if (m > max)
				max = m;
		}

		long normal = max / 5;
		return now.getVolume() < normal;
	}

	/**
	 * 5日内成交量平均振幅较小
	 */
	private boolean zhenFuLow(DayData dt) {
		Avg avg = new Avg();
		for (int i = 0; i < 5; i++) {
			DayData now = dt;
			DayData yes = now.getYestoday();

			if (yes == null)
				break;

			long volume = yes.getVolume();
			long nv = now.getVolume();

			double zhenFu = (nv - volume + 0d) / volume;

			zhenFu = Math.abs(zhenFu);

			avg.add(zhenFu);
			dt = yes;
		}
		return avg.getValue() < 0.35;
	}

	/**
	 * 50日内最高macd绝对值
	 * 
	 * @param i
	 * @param dt
	 * @return
	 */
	private double getMaxMacdAbs(DayData dt) {
		double max = Math.abs(dt.getMacd());
		for (int j = 0; j < 50; j++) {
			dt = dt.getYestoday();
			if (dt == null)
				break;
			double m = Math.abs(dt.getMacd());
			if (m > max)
				max = m;
		}
		return max;
	}

	public int getWinTimes() {
		return winTimes;
	}

	public int getLoseTimes() {
		return loseTimes;
	}
}
