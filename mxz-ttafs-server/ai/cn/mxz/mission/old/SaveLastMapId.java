package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import cn.mxz.mission.events.EnterEvent;
//import cn.mxz.user.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.UserCounter;
//
//public class SaveLastMapId extends MissionAdaptor {
//
//	@Override
//	public void onEnter(EnterEvent e) {
//
//		City city = e.getSouce();
//
//		UserCounter uc = city.getUserCounterHistory();
//
//		MissionMap map = city.getMission().getCurrentMap();
//
//		uc.add(CounterKey.LAST_MAP_ATTACK, map.getId());
//
//	}
//
//
//}
