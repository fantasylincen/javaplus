//package cn.mxz.activity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.mxz.event.EventDispatcher2Impl;
//import db.dao.factory.DaoFactory;
//
///**
// * 活动管理器, 用于统一管理Boss活动, 爬塔活动
// * @author 林岑
// *
// */
//public class ActivityManager extends EventDispatcher2Impl{
//
//	private static ActivityManager	instance;
//
//	private List<Activity>	activitys = new ArrayList<Activity>();
//
//	private ActivityManager() {
//	}
//
//	public static final ActivityManager getInstance() {
//		if (instance == null) {
//			instance = new ActivityManager();
//		}
//		return instance;
//	}
//
//	/**
//	 * 注册某个活动
//	 * @param activity
//	 */
//	public void regist(Activity activity) {
//
//		this.activitys.add(activity);
//	}
//
//	/**
//	 * 停止所有已经注册了的活动
//	 */
//	public void stopAll() {
//
//		for (Activity a : activitys) {
//
//			a.stop();
//		}
//
//		DaoFactory.getCacheActivityScoreRewardDAO().clear();
//
//	}
//}
