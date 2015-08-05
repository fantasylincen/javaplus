package org.hhhhhh.fqzs.desktop;

import java.util.List;

import org.hhhhhh.fqzs.login.LoginEvent;
import org.hhhhhh.fqzs.login.LoginListener;
import org.hhhhhh.fqzs.login.LoginUI;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

public class DesktopLoginUI implements LoginUI{

	private List<LoginListener> listeners = Lists.newArrayList();

	@Override
	public void addListener(LoginListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void show() {
		final LoginFrame frame = new LoginFrame();
		frame.addListener(new LoginFrameListener() {
			@Override
			public void onLoginSuccess(LoginEvent e) {
				frame.dispose();
				for (LoginListener l : listeners) {
					l.onLoginSuccess(e);
				}
			}
		});
		frame.setVisible(true);
		Log.d("show loginFrame");
	}

}
