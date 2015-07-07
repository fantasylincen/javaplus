package org.hhhhhh.prophet.config;

import org.dom4j.Element;

public class ServerNode {

	private final Element e;
	private final String gameName;

	public ServerNode(String gameName, Element e) {
		this.gameName = gameName;
		this.e = e;
	}

	public String getString(String key) {
		return e.elementTextTrim(key);
	}

	public double getDouble(String key) {
		return new Double(getString(key));
	}

	public String getGameName() {
		return gameName;
	}

}
