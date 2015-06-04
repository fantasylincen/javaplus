//package cn.mxz.listeners.guoqing;
//
//import cn.mxz.city.City;
//import cn.mxz.events.Listener;
//import cn.mxz.events.init.EnterGameEvent;
//
//public class SendGuoQingReward implements Listener<EnterGameEvent> {
//
//	@Override
//	public void onEvent(EnterGameEvent e) {
//		try {
//			City city = e.getCity();
//			city.getGuoQing().sendReward();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//	}
//}
