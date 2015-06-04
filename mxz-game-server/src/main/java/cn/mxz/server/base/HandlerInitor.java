package cn.mxz.server.base;

import cn.mxz.server.handlers.LoginHandler;
import cn.mxz.server.handlers.OneKeyRegistHandler;

public class HandlerInitor {

	public void init(GameServer s) {
		s.addHandler("login", new LoginHandler());
		s.addHandler("oneKeyRegist", new OneKeyRegistHandler());
	}

}
