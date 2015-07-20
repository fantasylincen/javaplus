package org.hhhhhh.guess.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class GameProperties {

	public static Long getLong(String k) {
		String v = getString(k);
		if (v == null) {
			return null;
		}
		return new Long(v);
	}

	public static String getString(String k) {

		String ss = getStringNoTrim(k);
		if (ss == null)
			return null;
		return ss.trim();
	}

	public static String getStringNoTrim(String k) {

		String s = (String) CacheManager.get(key(k));
		if (s != null)
			return s;

		SAXReader reader = new SAXReader();
		try {

			Document d = reader.read(Resources
					.getResource("game-properties.xml"));
			Element root = d.getRootElement();
			s = root.elementText(k);
		} catch (DocumentException e) {
			throw Util.Exception.toRuntimeException(e);
		}

		CacheManager.put(key(k), s);

		return s;
	}

	private static String key(String k) {

		String key = GameProperties.class.getName();
		return key + ":" + k;
	}
}
