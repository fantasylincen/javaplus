//package cn.mxz.present;
//
//import message.S;
//import cn.mxz.MarketPlaceTemplet;
//import cn.mxz.MarketPlaceTempletConfig;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.city.City;
//import cn.mxz.market.Goods;
//import cn.mxz.mission.old.GoldNeed;
//import cn.mxz.temp.TempCollection;
//import cn.mxz.temp.TempKey;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//public class PresentShopper {
//
//	private City city;
//
//	public PresentShopper(City city) {
//		this.city = city;
//	}
//
//	/**
//	 * 购买这个礼包
//	 * 
//	 * @param present
//	 * @param count
//	 */
//	public void buy(Present present, int count) {
//		check(present, count);
//		reduce(present, count);
//		add(present, count);
//		savePropThisTime(present.getId(), count, present.getNeeds() * count);
//	}
//
//	private void add(Present present, int count) {
//		present.send(city.getPlayer(), count);
//	}
//
//	private void reduce(Present present, int count) {
//		int need = present.getNeeds();
//		city.getPlayer().reduceGold(count * need);
//	}
//
//	private void check(Present present, int count) {
//
//		checkToday(present, count);
//		checkHistory(present, count);
//		checkVipLevel(present);
//
//		int need = present.getNeeds();
//		new GoldNeed(need * count).checkEnouph(city.getPlayer());
//	}
//
//	private void checkVipLevel(Present present) {
//		Integer id = present.getId();
//		MarketPlaceTemplet te = MarketPlaceTempletConfig.get(id);
//		if(city.getVipPlayer().getLevel() < te.getVipLevelNeed()) {
//			throw new OperationFaildException(S.S10235);
//		}
//	}
//
//	private void checkHistory(Present present, int count) {
//		Integer id = present.getId();
//		MarketPlaceTemplet te = MarketPlaceTempletConfig.get(id);
//		checkTimes(id, count, city.getUserCounter(), te.getMax());
//	}
//
//	private void checkTimes(Integer id, int count, UserCounter c, int max) {
//
//		int a = c.get(CounterKey.PRESENT_BUY_TIMES, id);
//
//		if (max == -1) {
//			return;
//		}
//
//		if (a + count > max) {
//			throw new OperationFaildException(S.S60137);
//		}
//	}
//
//	private void checkToday(Present present, int count) {
//		Integer id = present.getId();
//		MarketPlaceTemplet te = MarketPlaceTempletConfig.get(id);
//		checkTimes(id, count, city.getUserCounter(), te.getForevermax());
//	}
//
//	private void savePropThisTime(int toolId, int count, int price) {
//
//		TempCollection c = city.getTempCollection();
//
//		c.put(TempKey.BUY_PROP_THIS_TIME, new Goods(toolId, count, price));
//	}
//}
