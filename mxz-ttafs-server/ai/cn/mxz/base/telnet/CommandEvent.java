package cn.mxz.base.telnet;

import org.dom4j.Element;

public class CommandEvent {

	private Element currentElement;

	CommandEvent(Element currentElement) {
		this.currentElement = currentElement;
	}

	public Element getCurrentElement() {
		return currentElement;
	}

	public String getCommand() {

		Element e = currentElement.element("command");

		return e.getTextTrim();
	}

}
