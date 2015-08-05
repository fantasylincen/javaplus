package org.hhhhhh.fqzs.android;

import org.hhhhhh.fqzs.core.App;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		App app = App.getApp();
		app.setLoginUI(new AndroidLoginUI(this));
		app.setRoleUI(new AndroidRoleUI());
		app.setMessageUI(new AndroidMessageUI());
		initialize(app, config);
	}
}
