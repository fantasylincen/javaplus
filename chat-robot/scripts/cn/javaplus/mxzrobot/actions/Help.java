package cn.javaplus.mxzrobot.actions;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class Help extends ActionBase {

	@SuppressWarnings("unchecked")
	public String execute(Args args) {
		SAXReader r = new SAXReader();
		StringPrinter sp = new StringPrinter();
		List<Element> ls = Lists.newArrayList();
		try {
			Document doc = r.read(new File("config/asks.xml"));
			Element e = doc.getRootElement();
			List<Element> actions = e.elements("action");
			for (Element e2 : actions) {
				List<Element> asks = e2.elements("ask");
				for (Element ee : asks) {
					ls.add(ee);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Util.Collection.upset(ls);
		ls = Util.Collection.sub(ls, 50);
		sp.print("你可以输入如下内容:<br><br>");
		for (Element ee : ls) {
			sp.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + ee.getTextTrim() + "<br>");
		}
		return sp.toString();
	}

}
