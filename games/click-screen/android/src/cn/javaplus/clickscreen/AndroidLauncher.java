package cn.javaplus.clickscreen;

import android.os.Bundle;
import android.view.KeyEvent;
import cn.javaplus.clickscreen.android.AndroidOS;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.umeng.update.UmengUpdateAgent;

public class AndroidLauncher extends AndroidApplication {

	private static final String INSTALL_MARK = "has-install";

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		App.setOs(new AndroidOS(this));
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(App.getApp(), config);

		App.getLogger().onCountEvent("start");
		logInstall();
		UmengUpdateAgent.update(this); // 检查更新
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
