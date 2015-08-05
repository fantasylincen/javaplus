package cn.javaplus.shhz.log;

import cn.javaplus.shhz.util.Util;

public class SystemOut implements Out {

	@Override
	public void println(Object... message) {
		System.out.println(Util.Collection.linkWith("", message));
	}

}
