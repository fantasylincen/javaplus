//package cn.mxz.base.servertask;
//
//import java.util.Collection;
//
//import cn.mxz.pvp.PVPTaskManager;
//import cn.mxz.user.City;
//import cn.mxz.util.debuger.Debuger;
//
//class PvpTaskClear extends TaskSafetyLogToFile {
//
//	@Override
//	public void runSafty() {
//
//		Collection<City> nearests = getNearests();
//
//		Debuger.debug("PvpTaskClear.runSafty() 清空玩家pvp任务信息, 被清空的所有玩家数量:" + nearests.size());
//
//		for (City city : nearests) {
//
//			PVPTaskManager pvpTask = (PVPTaskManager) city.getPVPTask();
//
//			pvpTask.onZero();
//		}
//	}
//
//}
