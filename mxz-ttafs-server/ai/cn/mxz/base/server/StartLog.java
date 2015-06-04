package cn.mxz.base.server;

import java.util.List;
import java.util.ListIterator;

import cn.javaplus.util.Util;
import cn.mxz.util.debuger.SystemLog;

public class StartLog {

	private List<String>	lines;

	public void load(String path) {
		lines = Util.File.getLines(path);
	}

	public void print() {
		for (String text : lines) {
			SystemLog.info(text);
		}
	}

	public void replace(int index, Object text) {
		ListIterator<String> it = lines.listIterator();
		while (it.hasNext()) {
			String t = it.next();
			if(text == null) {
				throw new NullPointerException();
			}
			String string = text.toString();
			t = t.replaceAll("%S" + index, string);
			it.set(t);
		}
	}

}
