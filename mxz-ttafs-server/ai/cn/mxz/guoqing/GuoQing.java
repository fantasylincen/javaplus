//package cn.mxz.guoqing;
//
//import cn.javaplus.log.Debuger;
//import cn.javaplus.util.Util;
//import cn.mxz.NationalDayAwardTemplet;
//import cn.mxz.NationalDayAwardTempletConfig;
//import cn.mxz.city.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//public class GuoQing {
//
//	private City city;
//
//	public GuoQing(City city) {
//		this.city = city;
//	}
//
//	public void sendReward() {
//		if (isActivityOpen()) {
//			if (!isSend()) {
//				send();
//				Debuger.debug("GuoQing.sendReward() 发放国庆奖励 " + city);
//			}
//		}
//	}
//
//	private void send() {
//		sendToPrizeCenter();
//		markSend();
//	}
//
//
//	private void sendToPrizeCenter() {
//		int id = getIdWillSend();
//		NationalDayAwardTemplet temp = NationalDayAwardTempletConfig.get(id);
//		city.getPrizeCenter().addPrize(3, temp.getAwards(), temp.getLog(), temp.getLog());
//		setSendId(id);
//	}
//
//	private int getIdWillSend() {
//		UserCounter his = city.getUserCounterHistory();
//		int id = his.get(CounterKey.GUO_QING_REWARD_ID);
//		if(id == 0) {
//			return NationalDayAwardTempletConfig.getMinKey();
//		}
//		
//		if(NationalDayAwardTempletConfig.getMaxKey().equals(id)) {
//			return -1;
//		}
//		return id + 1;
//	}
//
//	private void setSendId(int id) {
//		UserCounter his = city.getUserCounterHistory();
//		his.set(CounterKey.GUO_QING_REWARD_ID, id);
//	}
//
//	private void markSend() {
//		UserCounter c = city.getUserCounter();
//		c.mark(CounterKey.GUO_QING);
//	}
//	
//	private boolean isSend() {
//		UserCounter c = city.getUserCounter();
//		return c.isMark(CounterKey.GUO_QING);
//	}
//
//	private boolean isActivityOpen() {
//		return Util.Time.isIn("2014-10-01|00:00 to 2014-10-08|00:00");
//	}
//
//}
