package cn.javaplus.mxzrobot.httpserver;

import java.util.List;

import org.dom4j.Element;

import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class AskAction {

	private Element actionNode;
	private List<String> examples = Lists.newArrayList();
	
	public AskAction(Element actionNode) {
		this.actionNode = actionNode;
		@SuppressWarnings("unchecked")
		List<Element> examples = actionNode.elements("example");
		for (Element e : examples) {
			this.examples.add(e.getTextTrim());
		}
	}

	public String getHtmlText() {
		String content = Util.File.getContent("config/categray2.temp");
		String head = actionNode.elementTextTrim("head");
		return content.replaceAll("CONTENT", head + getDsc());
	}

	private String getDsc() {
		String n = actionNode.elementTextTrim("dsc");
		String s = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		return s + n;
	}

}
