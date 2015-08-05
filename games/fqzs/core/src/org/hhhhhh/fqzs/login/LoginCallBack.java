package org.hhhhhh.fqzs.login;

public interface LoginCallBack {

	void loginSuccess(String id, String token);

	void loginFaild(String message);

}
