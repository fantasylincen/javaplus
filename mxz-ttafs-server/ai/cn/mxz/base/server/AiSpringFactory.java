package cn.mxz.base.server;

import cn.mxz.base.telnet.SpringFactory;
import cn.mxz.util.Factory;

public class AiSpringFactory implements SpringFactory {

	@Override
	public Object get(String key) {

		return Factory.get(key);
	}

}
