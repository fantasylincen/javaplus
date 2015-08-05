package cn.javaplus.clickscreen.android;
//package cn.javaplus.twolegs.android;
//
//import java.util.List;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import cn.javaplus.twolegs.App;
//import cn.javaplus.twolegs.Logger;
//import cn.javaplus.twolegs.util.Lists;
//
//import com.baidu.mobstat.StatService;
//
//public class BaiduLogger implements Logger {
//
//	private Activity activity;
//	private static List<Integer> scores;
//
//	public BaiduLogger(Activity activity) {
//		this.activity = activity;
//		if (scores == null) {
//			scores = Lists.newArrayList(3000, 2000, 1000, 900, 800, 700, 600,
//					500, 400, 300, 200, 100, 70, 69, 68, 67, 66, 65, 64, 63,
//					62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48,
//					47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33,
//					32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18,
//					17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
//					0);
//		}
//		try {
//			StatService.setAppChannel(App.getChannels().getName());
//			StatService.setAppKey("ccd71e8963");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onCreate() {
//		try {
//			StatService.onEvent(activity, "onStart", "onStart", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onRestart() {
//		try {
//			StatService.onEvent(activity, "onRestart", "onRestart", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onShowAds() {
//		try {
//			StatService.onEvent(activity, "showAds", "showAds", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressLint("UseValueOf")
//	@Override
//	public void onGameOver(String score, int touchTimes) {
//		float f;
//		try {
//			f = new Float(score);
//		} catch (NumberFormatException e) {
//			f = 0;
//		}
//
//		onScore(f);
//
//		try {
//			StatService.onEvent(activity, "touchTimes", "this time:"
//					+ touchTimes, touchTimes);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void onScore(Float f) {
//
//		for (Integer value : scores) {
//			if (f > value) {
//				score(value, f);
//			}
//		}
//
//	}
//
//	private void score(int score, Float svalue) {
//		try {
//			String s = "score-" + score;
//			StatService.onEvent(activity, s, "score:" + svalue, 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onInstall() {
//		try {
//			StatService.onEvent(activity, "install", "install", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onEvent(Object id, Object label, int times) {
//		try {
//			StatService.onEvent(activity, id.toString(), label.toString(),
//					times);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
