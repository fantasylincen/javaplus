//package cn.mxz.present;
//
//import java.util.List;
//
//import message.S;
//import cn.mxz.MarketPlaceTemplet;
//import cn.mxz.MarketPlaceTempletConfig;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.city.City;
//import cn.mxz.shop.Shopper;
//import cn.mxz.user.Player;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//import cn.mxz.util.counter.UserCounterSetter;
//
//import com.google.common.collect.Lists;
//
//import db.dao.impl.DaoFactory;
//import db.dao.impl.PresentsDao;
//import db.domain.Presents;
//import db.domain.PresentsImpl;
//
//public class PresentManagerImpl implements PresentManager {
//
//	private class PresentImpl implements Present {
//
//		private MarketPlaceTemplet temp;
//
//		public PresentImpl(MarketPlaceTemplet temp) {
//			this.temp = temp;
//		}
//
//		@Override
//		public int getNeeds() {
//			Shopper shopper = new Shopper(city);
//			return shopper.getPrice(temp.getTypeId(), 1).getGoldNew();
//		}
//
//		@Override
//		public Integer getId() {
//			return temp.getTypeId();
//		}
//
//		@Override
//		public void send(Player player, int count) {
//			city.getBag().addProp(temp.getTypeId(), count);
//		}
//
//		@Override
//		public int getBuyTimes() {
//			UserCounter c = city.getUserCounter();
//			return c.get(CounterKey.PRESENT_BUY_TIMES, temp.getTypeId());
//		}
//
//		@Override
//		public void markBuy() {
//			UserCounterSetter c = city.getUserCounterAuto();
//			c.add(CounterKey.PRESENT_BUY_TIMES, 1, temp.getTypeId());
//		}
//
//		@Override
//		public int getRemainBuyTime() {
//			return Math.min(getRemainBuyTimesToday(), getRemainBuyTimesHistory());
//		}
//
//		private int getRemainBuyTimesToday() {
//			int max = temp.getMax();
//			if(max == -1) {
//				return Integer.MAX_VALUE;
//			}
//			int buyTimes = getBuyTimes();
//			int re = max - buyTimes;
//			re = Math.max(re, 0);
//			return re;
//		}
//		
//
//		private int getRemainBuyTimesHistory() {
//			int max = temp.getForevermax();
//			if(max == -1) {
//				return Integer.MAX_VALUE;
//			}
//			int buyTimes = getBuyTimesHistory();
//			int re = max - buyTimes;
//			re = Math.max(re, 0);
//			return re;
//		}
//
//		private int getBuyTimesHistory() {
//			UserCounter c = city.getUserCounterHistory();
//			return c.get(CounterKey.PRESENT_BUY_TIMES, temp.getTypeId());
//		}
//
//	}
//
//	private City city;
//	private Presents presents;
//
//	public PresentManagerImpl(City city) {
//		this.city = city;
//		ensureNotNull();
//	}
//
//	private void ensureNotNull() {
//		PresentsDao DAO = DaoFactory.getPresentsDao();
//		presents = DAO.get(city.getId());
//
//		if (presents == null) {
//			presents = new PresentsImpl();
//			presents.setUname(city.getId());
//			DAO.add(presents);
//		}
//	}
//
//	@Override
//	public List<Present> getAll() {
//
//		List<Present> a = Lists.newArrayList();
//		List<MarketPlaceTemplet> all = MarketPlaceTempletConfig.findByTab(1);
//		for (MarketPlaceTemplet m : all) {
//			PresentImpl pr = new PresentImpl(m);
////			if(pr.getRemainBuyTime() > 0) {
//				a.add(pr);
////			}
//		}
//
//		return a;
//	}
//
//	@Override
//	public Present get(int id) {
//		List<Present> all = getAll();
//		for (Present present : all) {
//			if (present.getId() == id) {
//				return present;
//			}
//		}
//		throw new OperationFaildException(S.S10180);
//	}
//
//	@Override
//	public void buy(int id, int count) {
////		Present present = get(id);
//		
//		new Shopper(city).buy(id, count);
//
////		new PresentShopper(city).buy(present, count);
//
////		present.markBuy();
//	}
//
//}
