package cn.javaplus.jigsaw;

import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.log.Log;

import android.os.Bundle;
import android.view.KeyEvent;
import cn.javaplus.jigsaw.android.AndroidOS;
import cn.javaplus.jigsaw.channel.ChannelExp10086Cn;
import cn.javaplus.jigsaw.channel.DefaultChannel;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.update.UmengUpdateAgent;

public class AndroidLauncher extends AndroidApplication {

	private static final String INSTALL_MARK = "has-install";

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		App.setOs(new AndroidOS(this));
		App.setChannel(getChannel());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(App.getApp(), config);

		App.getLogger().onCountEvent("start");
		logInstall();
		UmengUpdateAgent.update(this); // 检查更新
	}

	private Channel getChannel() {
		String um = AnalyticsConfig.getChannel(this);
		Log.d("AndroidLauncher", "getChannel", um);
		if ("10086.cn".equals(um)) {
			return new DefaultChannel();
		}
		if ("10086.cn-exp".equals(um)) {
			return new ChannelExp10086Cn();
		}
		if ("360.cn".equals(um)) {
			return new DefaultChannel();
		}
		if ("wandoujia.com".equals(um)) {
			return new DefaultChannel();
		}
		if ("qq.com".equals(um)) {
			return new DefaultChannel();
		}

		throw new RuntimeException("无法识别的渠道:" + um);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		App.getOs().exit();
	}

	private void logInstall() {
		if (isRunFirst()) {
			App.getLogger().onCountEvent("install");
			markRun();
		}
	}

	public void onResume() {
		super.onResume();
		App.getLogger().onResume();
	}

	public void onPause() {
		super.onPause();
		App.getLogger().onPause();
	}

	private void markRun() {
		App.getPreferences().put(INSTALL_MARK, "yes");
	}

	private boolean isRunFirst() {
		String str = App.getPreferences().getString(INSTALL_MARK);
		return "yes".equals(str);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
	}
}
