package cn.javaplus.mxzrobot.actions;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public class Help extends ActionBase {

	@SuppressWarnings("unchecked")
	public String execute(Args args) {
		SAXReader r = new SAXReader();
		StringPrinter sp = new StringPrinter();
		List<Element> ls = Lists.newArrayList();
		try {
			Document doc = r.read(Resources.getResource("asks.xml"));
			Element e = doc.getRootElement();
			List<Element> actions = e.elements("action");
			for (Element e2 : actions) {
				List<Element> asks = e2.elements("ask");
				for (Element ee : asks) {
					ls.add(ee);
				}
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Util.Collection.upset(ls);
		ls = Util.Collection.sub(ls, 50);
		sp.println("你可以输入如下内容:");
		sp.println("");
		for (Element ee : ls) {
			sp.println("                  " + ee.getTextTrim());
		}
		sp.replaceAll("\r\n", "<br>");
		return sp.toString();
	}

}
