package org.javaplus.game.common.log;

import org.javaplus.game.common.util.Util;

public class SystemOut implements Out {

	@Override
	public void println(Object... message) {
		System.out.println(Util.Collection.linkWith("", message));
	}

}
