package org.javaplus.game.common.log;

import org.javaplus.game.common.util.Util;

public class SystemErr implements Out {

	@Override
	public void println(Object... message) {
		System.err.println(Util.Collection.linkWith("", message));
	}

}
