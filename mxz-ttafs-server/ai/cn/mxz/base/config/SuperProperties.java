package cn.mxz.base.config;

import cn.javaplus.file.IProperties;

public class SuperProperties {

	private IProperties e;

	public SuperProperties(IProperties e) {
		this.e = e;
	}

	public String getString(String string) {
		return e.getProperty(string);
	}

	public int getInt(String string) {
		String s = getString(string);
		return new Integer(s);
	}

	public boolean getBoolean(String string) {
		String s = getString(string);
		return "true".equals(s) || "1".equals(s);
	}

}
