package org.hhhhhh.fqzs.core;

import java.util.Map;

import com.google.common.collect.Maps;

public class Translator {

	static Map<String, String> translate = createTranslate();

	private static Map<String, String> createTranslate() {
		Map<String, String> m = Maps.newHashMap();
		m.put("A", "禽");
		m.put("B", "银");
		m.put("C", "金");
		m.put("D", "兽");
		m.put("E", "燕");
		m.put("F", "鸽");
		m.put("G", "孔");
		m.put("H", "鹰");
		m.put("I", "狮");
		m.put("J", "熊");
		m.put("K", "猴");
		m.put("L", "兔");
		return m;
	}
	public String translate(String type) {
		return translate.get(type);
	}

}
