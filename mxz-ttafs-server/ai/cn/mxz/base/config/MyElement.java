package cn.mxz.base.config;

import org.dom4j.Element;

public class MyElement {

	private Element e;

	public MyElement(Element e) {
		this.e = e;
	}

	public int getInt(String aName) {
		return new Integer(att(aName));
	}

	private String att(String aName) {
		String a = e.attributeValue(aName);
		if(a == null) {
			throw new NullPointerException(aName);
		}
		return a.trim();
	}

	public String getString(String aName) {
		return att(aName);
	}

	public Boolean getBoolean(String aName) {
		return new Boolean(att(aName));
	}
}
