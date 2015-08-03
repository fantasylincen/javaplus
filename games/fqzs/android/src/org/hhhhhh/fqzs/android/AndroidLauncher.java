package org.hhhhhh.fqzs.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		
//		App app = App.getApp();
//		app.setLoginUI(new AndroidLoginUI(this));
//		app.setRoleUI(new AndroidRoleUI());
//		app.setMessageUI(new AndroidMessageUI());
//		initialize(app, config);
		setContentView(R.layout.activity_main);
	}
}
