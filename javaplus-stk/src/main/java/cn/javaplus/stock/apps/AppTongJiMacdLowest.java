package cn.javaplus.stock.apps;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.StockReader;

/**
 * tong ji macd zuididian jingchangchuxianzaidijitian
 */
public class AppTongJiMacdLowest {

	/**
	 * MACD 负值区域块
	 */
	public static class MacdNegative {

		private List<OneDayData> dayDatas = Lists.newArrayList();

		public List<OneDayData> getDayDatas() {
			return dayDatas;
		}

		public void add(OneDayData o) {
			dayDatas.add(o);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		StockReader r = new StockReader();
		List<Stock1> all = r.readShenHuA();

		Counter<Integer> counter = new Counter<>();

		for (Stock1 stock : all) {
			List<OneDayData> datas = stock.getDayDatas();
			tongJi(datas, counter);
		}

		for (Integer key : counter.keySet()) {
			int count = counter.get(key);
			System.out.println(key + " 次数 " + count);
		}
	}

	private static void tongJi(List<OneDayData> datas, Counter<Integer> counter) {
		List<MacdNegative> macds = getMacdNegatives(datas);
		for (MacdNegative m : macds) {
			List<OneDayData> ds = m.getDayDatas();
			double min = 1;
			int minIndex = -1;
			for (int i = 0; i < ds.size(); i++) {
				OneDayData o = ds.get(i);

				double macd = o.getMacd();
				if (macd < min) {
					min = macd;
					minIndex = i;
				}
			}
			counter.add(minIndex);
		}
	}

	private static List<MacdNegative> getMacdNegatives(List<OneDayData> datas) {
		ArrayList<MacdNegative> ls = Lists.newArrayList();

		MacdNegative m = null;
		for (OneDayData o : datas) {
			double macd = o.getMacd();

			if (macd < 0) {
				if (m == null) {
					m = new MacdNegative();
				}
				m.add(o);
			} else {
				if (m != null) {
					ls.add(m);
					m = null;
				}
			}
		}
		return ls;
	}

}
