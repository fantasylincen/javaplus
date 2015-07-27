package cn.javaplus.crazy.log;

import cn.javaplus.crazy.util.Util;

public class SystemErr implements Out {

	@Override
	public void println(Object... message) {
		System.err.println(Util.Collection.linkWith("", message));
	}

}
