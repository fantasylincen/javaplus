package cn.javaplus.crazy.log;

import cn.javaplus.crazy.util.Util;

public class SystemOut implements Out {

	@Override
	public void println(Object... message) {
		System.out.println(Util.Collection.linkWith("", message));
	}

}
