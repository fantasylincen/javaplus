package cn.javaplus.monichaogu.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;

@Dao
interface Price {

	String getDate();

	double getOpen();
	double getClose();
	double getHigh();
	double getLow();
	
	double getUp();
	int getVolume();
	
	double getAvg5();
	double getAvg10();
	double getAvg15();
	double getAvg20();
	double getAvg30();
	double getAvg45();
	double getAvg60();
	double getAvg100();
	double getAvg120();
	
	double getMax30();
	double getMax60();
	double getMax90();
	
}
