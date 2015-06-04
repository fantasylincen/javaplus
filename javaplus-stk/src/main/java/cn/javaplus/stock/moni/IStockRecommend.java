package cn.javaplus.stock.moni;

import cn.javaplus.stock.stock.DayData;

public interface IStockRecommend extends Comparable<IStockRecommend>{

	int getPriority();

	DayData getCurrentDay();

}