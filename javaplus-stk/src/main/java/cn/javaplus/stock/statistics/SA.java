package cn.javaplus.stock.statistics;

import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;

/**
 * 分析股票 某个涨跌值 明日平均涨跌值
 */
public class SA implements Foreach {

	double min = -0.096;
	double max = -0.09;
	
	static int count_all;
	static double stock_up_all;
	
	public void iterator(Stock1 stock) {
		List<OneDayData> ps = stock.getDayDatas();
		double all = 0;
		int count = 0;
		for (OneDayData tomorrow : ps) {
			OneDayData today = tomorrow.getYestoday();
			if(today == null)
				continue;
			
			double up = today.getUp();
			if(up > min && up < max) {
				all += tomorrow.getUp();
				count++;
			}
		}
		
		if(count ==0 )
			return;
		
		stock_up_all += all;
		count_all += count;
		
		Log.d(stock.getId() ,  all / count , stock_up_all / count_all);
	}

}
