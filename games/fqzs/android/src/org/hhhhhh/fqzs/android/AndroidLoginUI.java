package org.hhhhhh.fqzs.android;

import java.util.ArrayList;

import org.hhhhhh.fqzs.login.LoginListener;
import org.hhhhhh.fqzs.login.LoginUI;
import org.javaplus.game.common.util.Lists;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public class AndroidLoginUI implements LoginUI {

	private ArrayList<LoginListener> listeners;
	private Activity activity;

	public AndroidLoginUI(Activity activity) {
		this.activity = activity;
		listeners = Lists.newArrayList();
	}
	@Override
	public void addListener(LoginListener listener) {
		listeners.add(listener);
	}

	@Override
	public void show() {
		View v = new ImageView(activity)
		;
	activity.addContentView(v, null);
		activity.setContentView(R.layout.login);
	}

}
