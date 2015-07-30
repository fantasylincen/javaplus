package org.hhhhhh.fqzs.login;

/**
 * 角色选定界面
 */
public interface RoleUI {

	void addListener(RoleUIListener listener);

	void show(String id, String token);

}
