package cn.javaplus.twolegs;

import android.os.Bundle;
import android.view.KeyEvent;
import cn.javaplus.twolegs.android.AndroidOS;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {


	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		App.setOs(new AndroidOS(this));
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(App.getApp(), config);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		App.getOs().exit();
	}

	public void onResume() {
		super.onResume();
		App.getLogger().onResume();
	}

	public void onPause() {
		super.onPause();
		App.getLogger().onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
	}
}
