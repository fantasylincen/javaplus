package cn.mxz.chuangzhen;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class AbstactRankingListPro {

	protected final int count;
	protected final ChuangZhenPlayer player;
	
	public AbstactRankingListPro(int count, ChuangZhenPlayer player) {
		this.count = count;
		this.player = player;
	}

	public int getRankYestoday() {
		int yestoday = getDay() - 1;
	
		String id = player.getId();
		City city = CityFactory.getCity(id);
		UserCounter his = city.getUserCounterHistory();
	
		return his.get(CounterKey.CHUANG_ZHEN_RANK, yestoday);
	}

	public static final int getDay() {
		long m = Util.Time.getCurrentTimeMillis();
		m -= 6 * Util.Time.MILES_ONE_HOUR;
		return Util.Time.getDay(m);
	}
	
//	public static void main(String[] args) {
//		while(true) {
//			int day = getDay();
//			System.out.println(day);
//			Util.Thread.sleep(1000);
//		}
//	} 

	public int getRank() {
		ChuangZhenRankingList ins = ChuangZhenRankingList.getInstance();
		int rank = ins.getRank(player);
		ins.updateTodayRank(player);
		return rank;
	}
}