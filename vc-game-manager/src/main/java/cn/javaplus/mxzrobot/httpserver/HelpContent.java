package cn.javaplus.mxzrobot.httpserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.collections.map.ListMap;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class HelpContent {

	public String getValue() {
		StringPrinter out = new StringPrinter();
		List<Categray> cs = getCategrys();
		for (Categray c : cs) {
			String content = Util.File.getContent("config/categray1.temp");
			content = content.replaceAll("CONTENT", c.getCategray());
			out.print(content);

			out.print(c.getChildsHtml());
		}
		return out.toString();
	}

	private List<Categray> getCategrys() {
		ListMap<String, AskAction> ls = new ListMap<String, AskAction>();
		List<Element> actions = getActions();
		for (Element actionNode : actions) {
			String ca = actionNode.elementTextTrim("categray");
			if (ca == null) {
				ca = "默认";
			}
			AskAction action = new AskAction(actionNode);
			ls.put(ca, action);
		}

		ArrayList<Categray> ss = Lists.newArrayList();

		for (String s : ls.keySet()) {
			List<AskAction> list = ls.get(s);
			if (!list.isEmpty()) {
				ss.add(new Categray(s, list));
			}
		}

		return ss;
	}

	private List<Element> getActions() {
		SAXReader r = new SAXReader();
		try {
			Document doc = r.read(new File("config/asks.xml"));
			Element e = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> actions = e.elements("action");
			return actions;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
