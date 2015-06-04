package cn.mxz.listeners;
//package cn.mxz.city;
//
//import message.S;
//import cn.mxz.city.City;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.event.Listener2;
//import cn.mxz.events.PlayerPropertyChangeEvent;
//import cn.mxz.util.debuger.Debuger;
//
//public class ShowGoldAdd implements Listener2<PlayerPropertyChangeEvent> {
//
//	@Override
//	public void onEvent(PlayerPropertyChangeEvent e) {
//
//		if (e.isAdd()) {
//
//			City city = e.getCity();
//			PlayerProperty p = e.getPlayerProperty();
//
//			if (e.hasGoldChanged()) {
//				city.getMessageSender().send(S.S10159, e.getAdd());
//				Debuger.debug("PlayerPropertyAddListener.onEvent() 消息提示元宝增加了");
//
//			} else if (p == PlayerProperty.CASH) {
//
//				city.getMessageSender().send(S.S10158, e.getAdd());
//			}
//
//		}
//	}
//
//}
