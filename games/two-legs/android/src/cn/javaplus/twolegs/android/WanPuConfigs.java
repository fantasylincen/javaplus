//package cn.javaplus.twolegs.android;
//
//import android.app.Activity;
//import cn.javaplus.twolegs.Configs;
//import cn.javaplus.twolegs.log.Log;
//import cn.waps.AppConnect;
//
//public class WanPuConfigs implements Configs {
//
//	private Activity activity;
//
//	public WanPuConfigs(Activity activity) {
//		this.activity = activity;
//	}
//
//	@Override
//	public String getConfig(String key, String defaultValue) {
//		String value;
//		try {
//			AppConnect instance = AppConnect.getInstance(activity);
//			value = instance.getConfig(key, defaultValue);
//			Log.d("getConfig", key ,value);
//		} catch (Throwable e) {
//			return defaultValue;
//		}
//		return value;
//	}
//
//}
