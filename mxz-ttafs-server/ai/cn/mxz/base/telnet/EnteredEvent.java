package cn.mxz.base.telnet;

import org.dom4j.Element;

public class EnteredEvent {

	private Element currentElement;

	EnteredEvent(Element currentElement) {
		this.currentElement = currentElement;
	}

	public Element getCurrentElement() {
		return currentElement;
	}
}
