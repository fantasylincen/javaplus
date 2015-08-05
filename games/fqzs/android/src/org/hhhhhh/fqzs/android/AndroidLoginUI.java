package org.hhhhhh.fqzs.android;

import java.util.ArrayList;

import org.hhhhhh.fqzs.login.LoginListener;
import org.hhhhhh.fqzs.login.LoginUI;
import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class AndroidLoginUI implements LoginUI {

	private ArrayList<LoginListener> listeners;
	private Activity activity;
	private View username;
	private View password;
	private Button logginButton;

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
		activity.setContentView(R.layout.login);
		if (username == null) {
			username = activity.findViewById(R.id.username);
			password = activity.findViewById(R.id.password);
			logginButton = (Button) activity.findViewById(R.id.loginButton);
		}

	}

}
