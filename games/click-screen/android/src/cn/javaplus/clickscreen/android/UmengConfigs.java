package cn.javaplus.clickscreen.android;

import org.javaplus.game.common.Configs;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

public class UmengConfigs implements Configs {

	private Activity activity;

	public UmengConfigs(final Activity activity) {
		this.activity = activity;
		MobclickAgent.updateOnlineConfig(activity);
	}

	@Override
	public String getConfig(String key, String defaultValue) {
		try {
			String value = MobclickAgent.getConfigParams(activity, key);
			if (value == null) {
				return defaultValue;
			}
			return value;
		} catch (Throwable e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

}
