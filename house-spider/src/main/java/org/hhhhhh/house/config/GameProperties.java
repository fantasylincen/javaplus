package org.hhhhhh.house.config;

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
		if(ss == null)
			return null;
		return ss.trim();
	}
	
	public static String getStringNoTrim(String k) {

		SAXReader reader = new SAXReader();
		String s;
		try {
			
			Document d = reader.read(Resources
					.getResource("game-properties.xml"));
			Element root = d.getRootElement();
			s = root.elementText(k);
		} catch (DocumentException e) {
			throw Util.Exception.toRuntimeException(e);
		}

		return s;
	}

}
