package cn.javaplus.stock.stock;

import cn.javaplus.stock.gen.dto.MongoGen.DayDto;

public class DayData implements Comparable<DayData> {

	private DayDto dto;
	private DayData yestoday;
	private DayData tommorrow;
	private Integer dateInt;

	/**
	 * N日内   macd最大绝对值
	 * @param day
	 * @return
	 */
	public double getMaxMacdAbs(int day) {
		double max = Math.abs(getMacd());
		DayData dt = this;
		for (int j = 0; j < day; j++) {
			dt = dt.getYestoday();
			if (dt == null)
				break;
			double m = Math.abs(dt.getMacd());
			if (m > max)
				max = m;
		}
		return max;
	}

	public DayData(DayDto dto) {
		this.dto = dto;
	}

	public String getDate() {
		return dto.getDate();
	}

	public double getMax2() {
		return dto.getMax2();
	}

	public double getMax3() {
		return dto.getMax3();
	}

	public double getMax4() {
		return dto.getMax4();
	}

	public double getMax5() {
		return dto.getMax5();
	}

	public double getMax6() {
		return dto.getMax6();
	}

	public double getMax10() {
		return dto.getMax10();
	}

	public double getMax15() {
		return dto.getMax15();
	}

	public double getMax20() {
		return dto.getMax20();
	}

	public double getMacd() {
		return dto.getMacd();
	}

	@Override
	public int compareTo(DayData o) {
		return dto.getDate().compareTo(o.getDate());
	}

	public double getOpen() {
		return dto.getOpen();
	}

	public String getId() {
		return dto.getId();
	}

	public double getClose() {
		return dto.getClose();
	}

	public double getHigh() {
		return dto.getHigh();
	}

	public double getLow() {
		return dto.getLow();
	}

	public double getUp() {
		return dto.getUp();
	}
	
	
	
	
	
	
	/**
	 * 实际价格和复权后价格的比值
	 */
	public double getPercentOfFuQuan() {
		return dto.getPercentOfFuQuan();
	}
	
	
	
	
	
	
	
	public double getCloseReal() {
		return getClose() * getPercentOfFuQuan();
	}

	public double getHighReal() {
		return getHigh() * getPercentOfFuQuan();
	}

	public double getLowReal() {
		return getLow() * getPercentOfFuQuan();
	}

	public double getUpReal() {
		return getUp() * getPercentOfFuQuan();
	}
	
	

	public long getVolume() {
		return dto.getVolume();
	}

	public double getAvg5() {
		return dto.getAvg5();
	}

	public double getAvg10() {
		return dto.getAvg10();
	}

	public double getAvg15() {
		return dto.getAvg15();
	}

	public double getAvg20() {
		return dto.getAvg20();
	}

	public double getAvg30() {
		return dto.getAvg30();
	}

	public double getAvg45() {
		return dto.getAvg45();
	}

	public double getAvg60() {
		return dto.getAvg60();
	}

	public double getAvg100() {
		return dto.getAvg100();
	}

	public double getAvg120() {
		return dto.getAvg120();
	}

	public double getMax30() {
		return dto.getMax30();
	}

	public double getMax60() {
		return dto.getMax60();
	}

	public double getMax90() {
		return dto.getMax90();
	}

	public double getDea() {
		return dto.getDea();
	}

	public double getEmaShort() {
		return dto.getEmaShort();
	}

	public double getEmaLong() {
		return dto.getEmaLong();
	}

	public double getDiff() {
		return dto.getDiff();
	}

	
	public void setYestoday(DayData yestoday) {
		this.yestoday = yestoday;
	}

	public DayData getYestoday() {
		return yestoday;
	}

	public void setTomorrow(DayData tommorrow) {
		this.tommorrow = tommorrow;
	}

	public DayData getTommorrow() {
		return tommorrow;
	}

	public double getAvgOpenClose() {
		return getOpen() / 2 + getClose() / 2;
	}

	@Override
	public String toString() {
		return dto.getId() + ":" + dto.getDate();
	}

	public int getDateInt() {
		if (dateInt == null)
			dateInt = new Integer(dto.getDate());
		return dateInt;
	}

	/**
	 * 在我之前 有多少根macd绿线
	 * 
	 * @return
	 */
	public int getMacdGreenCountBefore() {
		return getMacdCountBefore(false);
	}

	private int getMacdCountBefore(boolean isRed) {
		int count = 0;
		DayData day = this;
		while (true) {
			day = day.getYestoday();
			if (day == null)
				break;
			double macd = day.getMacd();
			if (isRed) {
				if (macd < 0)
					break;
			} else {
				if (macd > 0)
					break;
			}

			count++;
		}
		return count;
	}

	public int getMacdRedCountBefore() {
		return getMacdCountBefore(true);
	}

	/**
	 * macd为负
	 */
	public boolean isMacdGreen() {
		return getMacd() < 0;
	}

	public DayData getTommorrow(int days) {
		DayData day = this;
		for (int i = 0; i < days; i++) {
			if (day == null)
				return null;
			day = day.getTommorrow();
		}
		return day;
	}

	public DayData getYestoday(int days) {
		DayData day = this;
		for (int i = 0; i < days; i++) {
			if (day == null)
				return null;
			day = day.getYestoday();
		}
		return day;
	}

	/**
	 * 涨停?
	 */
	public boolean isZhangTing() {
		return getUp() > 0.095;
	}

	/**
	 * 跌停?
	 */
	public boolean isDieTing() {
		return getUp() < -0.095;
	}
}
