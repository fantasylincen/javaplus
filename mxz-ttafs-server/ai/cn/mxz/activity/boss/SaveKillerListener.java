//package cn.mxz.activity.boss;
//
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import db.domain.BossData;
//
//@Listen(eventClass = AfterBeAttackEvent.class)
//public class SaveKillerListener implements Listener {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//		AfterBeAttackEvent event = (AfterBeAttackEvent) e;
//		BossImpl boss = (BossImpl) e.getSource();
//		BossData dto = boss.getDto();
//
//		if (boss.isDeath()) {
//			dto.setKiller(event.getCity().getId());
//			boss.commit();
//		}
//	}
//}
