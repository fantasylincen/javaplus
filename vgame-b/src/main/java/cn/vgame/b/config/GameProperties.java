package cn.vgame.b.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.util.Util;
import cn.vgame.share.CacheManager;

import com.google.common.io.Resources;

public class GameProperties {

	public static Long getLong(String k) {
		String v = getString(k);
		if(v == null) {
			return null;
		}
		return new Long(v);
	}
	
	public static String getString(String k) {

		String key = key(k);
		String s = (String) CacheManager.get(key);
		if (s != null)
			return s;

		SAXReader reader = new SAXReader();
		try {
			Document d = reader.read(Resources.getResource("game-properties.xml"));
			Element root = d.getRootElement();
			s = root.elementTextTrim(k);
		} catch (DocumentException e) {
			throw Util.Exception.toRuntimeException(e);
		}

		CacheManager.put(key, 60000, s);

		return s;
	}

	private static String key(String k) {

		String key = GameProperties.class.getName();
		return key + ":" + k;
	}
}
