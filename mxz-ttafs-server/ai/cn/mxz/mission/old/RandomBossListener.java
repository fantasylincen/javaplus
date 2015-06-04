package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.IgnoreEventTempletConfig;
//import cn.mxz.activity.boss.Boss;
//import cn.mxz.activity.boss.BossFactory;
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.mission.person.PersonAfterMoveEvent;
//import cn.mxz.user.City;
//import cn.mxz.util.debuger.Debuger;
//
//@Listen(eventClass = PersonAfterMoveEvent.class)
//public class RandomBossListener implements Listener {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		PersonAfterMoveEvent event = (PersonAfterMoveEvent) e;
//
//		Person person = (Person) event.getSource();
//
//		City city = person.getCity();
//
//		Location l = person.getLocation();
//
//		randomBoss(l.getPath(), l.getIndex(), city);
//
//		Debuger.debug("RandomBossListener.onEvent() 随机Boss");
//	}
//
//	/**
//	 * 随机产生Boss
//	 * @param path
//	 * @param index
//	 * @param city
//	 */
//	private void randomBoss(int path, int index, City city) {
//
//		// 在我遇到的Boss没有被击杀之前移动, 不会遇到其他Boss
//
//		cn.mxz.activity.boss.BossMission ms = city.getBossMission();
//
//		List<Boss> boss = ms.getBossFoundByMe();
//
//		for (Boss b : boss) {
//
//			if(!b.isDeath() && !b.isRunAway()) {
//
//				return;
//			}
//		}
//
//		MissionMap currentMap = city.getBossMission().getCurrentMap();
//		
//		MapNode pn = currentMap.getPersonNode();
//
//		if(pn.isEmpty()) {
//
//			if(isBossHappen()) {
//
//				BossFactory.generateBoss(city, currentMap.getId());
//			}
//		}
//	}
//
//
//	/**
//	 * 如果Boss事件发生了
//	 * @return
//	 */
//	private boolean isBossHappen() {
//
//		int[] ls = IgnoreEventTempletConfig.getArrayByBossEventWeight();
//
//		int index = Util.getRandomIndex(ls);
//
//		return index == 2;
//	}
//}
