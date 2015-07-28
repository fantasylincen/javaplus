package cn.javaplus.shhz.log;

import cn.javaplus.shhz.util.Util;

public class SystemErr implements Out {

	@Override
	public void println(Object... message) {
		System.err.println(Util.Collection.linkWith("", message));
	}

}
