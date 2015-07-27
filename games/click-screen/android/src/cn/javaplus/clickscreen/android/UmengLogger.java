package cn.javaplus.clickscreen.android;

import java.util.Map;

import org.javaplus.game.common.Logger;
import org.javaplus.game.common.log.Log;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

public class UmengLogger implements Logger {

	private Activity activity;

	public UmengLogger(Activity activity) {
		this.activity = activity;

	}

	@Override
	public void onCountEvent(Object id) {

		try {
			MobclickAgent.onEvent(activity, id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("UmengLogger", "onCountEvent", id);

	}

	@Override
	public void onEvent(Object id, Map<String, String> kvs) {

		try {
			MobclickAgent.onEvent(activity, id.toString(), kvs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("UmengLogger", "onEvent", id, kvs);

	}

	public void onResume() {

		try {
			MobclickAgent.onPageStart("AndroidLauncher"); // 统计页面
			MobclickAgent.onResume(activity); // 统计时长
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("UmengLogger", "onResume");

	}

	public void onPause() {

		try {
			MobclickAgent.onPageEnd("AndroidLauncher"); // 保证 onPageEnd 在onPause
														// 之前调用,因为 onPause
														// 中会保存信息
			MobclickAgent.onPause(activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("UmengLogger", "onPause");

	}
}
