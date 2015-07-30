package org.hhhhhh.fqzs.desktop;

import java.util.List;

import org.hhhhhh.fqzs.login.RoleSelectedEvent;
import org.hhhhhh.fqzs.login.RoleUI;
import org.hhhhhh.fqzs.login.RoleUIListener;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

public class DesktopRoleUI implements RoleUI {

	private List<RoleUIListener> listeners = Lists.newArrayList();

	@Override
	public void addListener(RoleUIListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void show(String id, String token) {
		final RoleFrame frame = new RoleFrame(id, token);
		frame.addListener(new RoleFrameListener() {
			@Override
			public void onRoleSelectedSuccess(RoleSelectedEvent e) {
				frame.dispose();
				for (RoleUIListener l : listeners) {
					l.onRoleSelected(e);
				}
			}
		});
		frame.setVisible(true);
		Log.d("show roleFrame");
	}

}
