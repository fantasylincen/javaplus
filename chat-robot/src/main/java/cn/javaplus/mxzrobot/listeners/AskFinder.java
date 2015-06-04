package cn.javaplus.mxzrobot.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.mxzrobot.events.Ask;
import cn.javaplus.mxzrobot.events.Ask.AskWay;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class AskFinder {

	public Ask find(String c) {
		c = trim(c);

		List<Ask> all = getAsks(c);
		for (Ask ask : all) {
			if (ask.matches()) {
				return ask;
			}
		}
		return null;
	}

	private String trim(String c) {
		c = c.replaceAll("\\s", "");
		return c;
	}

	@SuppressWarnings("unchecked")
	private List<Ask> getAsks(String c) {
		SAXReader r = new SAXReader();
		List<Ask> ls = Lists.newArrayList();
		try {
			Document doc = r.read(new File("config/asks.xml"));
			Element e = doc.getRootElement();
			List<Element> actions = e.elements("action");
			for (Element e2 : actions) {
				List<Element> asks = e2.elements("ask");
				Element answer = e2.element("answer");
				Ask ask = new Ask(getTexts(asks), answer, c);
				ls.add(ask);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	private List<String> getTexts(List<Element> asks) {
		ArrayList<String> ls = Lists.newArrayList();
		for (Element e : asks) {
			ls.add(e.getTextTrim());
		}
		return ls;
	}

	/**
	 * 查出最相似的几个
	 * 
	 * @param heSay
	 * @return
	 */
	public List<AskWay> findSimilar(String heSay) {
		heSay = trim(heSay);
		List<AskWay> all = getAskWays(heSay);
		Collections.sort(all, new SortBySimilar(heSay));
		return Util.Collection.sub(all, 10);
	}

	private List<AskWay> getAskWays(String heSay) {
		List<Ask> asks = getAsks(heSay);
		List<AskWay> ls = Lists.newArrayList();
		for (Ask ask : asks) {
			List<AskWay> all = ask.getAskWays();
			ls.addAll(all);
		}
		return ls;
	}

}
