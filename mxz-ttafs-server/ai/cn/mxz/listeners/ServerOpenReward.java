package cn.mxz.listeners;
//package cn.mxz.system;
//
//import cn.javaplus.util.Util;
//import cn.mxz.OpenServicerTemplet;
//import cn.mxz.OpenServicerTempletConfig;
//import cn.mxz.base.config.ServerConfig;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.city.City;
//import cn.mxz.event.Listener2;
//import cn.mxz.events.UserCreateEvent;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//public class ServerOpenReward implements Listener2<UserCreateEvent> {
//
//	@Override
//	public void onEvent(UserCreateEvent event) {
//		City c = event.getCity();
//		int dayOpen = ServerConfig.getServerOpenDay();
//		int dayNow = Util.Time.getCurrentDay();
//
//		int day = dayNow - dayOpen + 1;
//
//		if (!isTimeUp(day)) {
//			sendReward(c, day);
//		}
//	}
//
//	private boolean isTimeUp(int day) {
//		OpenServicerTemplet t = OpenServicerTempletConfig.get(day);
//		return t == null;
//	}
//
//	private void sendReward(City c, int day) {
//		if (!hasReceive(c)) {
//			award(c, day);
//			mark(c);
//		}
//	}
//
//	private void award(City c, int day) {
//		OpenServicerTemplet t = OpenServicerTempletConfig.get(day);
//		String award = t.getAward();
//		PrizeSender s = PrizeSenderFactory.getPrizeSender();
//		s.send(c.getPlayer(), award);
//	}
//
//	private void mark(City c) {
//		UserCounter hs = c.getUserCounterHistory();
//		hs.mark(CounterKey.HAS_RECEIVE_OPEN_SERVER_REWARD);
//	}
//
//	private boolean hasReceive(City c) {
//		UserCounter hs = c.getUserCounterHistory();
//		return hs.isMark(CounterKey.HAS_RECEIVE_OPEN_SERVER_REWARD);
//	}
//}
