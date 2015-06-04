//package cn.mxz.activity.boss;
//
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import db.domain.BossData;
//
//@Listen(eventClass = AfterBeAttackEvent.class)
//public class SaveAttackTimesListener implements Listener {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//		BossImpl boss = (BossImpl) e.getSource();
//		BossData dto = boss.getDto();
//		dto.addChallengeTimes(1);
//		boss.commit();
//	}
//
//}
