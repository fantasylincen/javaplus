//package cn.mxz.listeners;
//
//import cn.mxz.events.Listener;
//import cn.mxz.events.PlayerHeroLevelUpEvent;
//import cn.mxz.shenmo.ShenmoManager;
//
///**
// * 15级时 ShenmoManager.INSTANCE.create(e.getCity());
// * @author 林岑
// *
// */
//public class PlayerHeroLevelUpCreateShenMo implements
//		Listener<PlayerHeroLevelUpEvent> {
//
//	@Override
//	public void onEvent(PlayerHeroLevelUpEvent e) {
//		if (e.getLevel() == 15) {
//			try {
//				ShenmoManager.INSTANCE.create(e.getCity());
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//
//}
