package org.hhhhhh.fqzs.android;

import java.util.ArrayList;

import org.hhhhhh.fqzs.login.RoleUI;
import org.hhhhhh.fqzs.login.RoleUIListener;
import org.javaplus.game.common.util.Lists;

public class AndroidRoleUI implements RoleUI {

	private ArrayList<RoleUIListener> listeners;

	public AndroidRoleUI() {
		listeners = Lists.newArrayList();
	}

	@Override
	public void addListener(RoleUIListener listener) {
		listeners.add(listener);
	}

	@Override
	public void show(String id, String token) {
		// TODO Auto-generated method stub

	}

}
