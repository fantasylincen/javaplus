package cn.javaplus.stock.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;

@Dao
interface Day {
	String getId();

	String getDate();

	double getOpen();

	double getClose();

	double getHigh();

	double getLow();

	double getUp();

	long getVolume();

	double getAvg5();

	double getAvg10();

	double getAvg15();

	double getAvg20();

	double getAvg30();

	double getAvg45();

	double getAvg60();

	double getAvg100();

	double getAvg120();

	double getMax2();
	double getMax3();
	double getMax4();
	double getMax5();
	double getMax6();
	double getMax10();
	double getMax15();
	double getMax20();
	double getMax30();

	double getMax60();

	double getMax90();

	double getDea();

	double getMacd();

	double getEmaShort();

	double getEmaLong();

	double getDiff();
	
	/**
	 * 实际价格和复权后价格的比值
	 */
	double getPercentOfFuQuan();

	/**
	 * 最高价最低价振幅
	 */
	double getHighLowZhenFu();

	/**
	 * 开盘收盘价振幅
	 */
	double getOpenCloseZhenFu();
}
