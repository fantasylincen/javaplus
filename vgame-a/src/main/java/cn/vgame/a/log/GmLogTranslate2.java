package cn.vgame.a.log;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.util.Resources;
import cn.javaplus.util.Util;
import cn.vgame.a.gen.dto.MongoGen.GmLogDto;

public class GmLogTranslate2 {

	static Map<String, String> translate = init();

	public static String translate(GmLogDto dto) {

		String className = dto.getClassName();
		String methodName = dto.getMethodName();

		String trans = translate.get(className + "." + methodName);
		if (trans == null)
			return "未知";
		
		return trans ;
	}

	private static Map<String, String> init() {
		Properties p = Util.Property.getProperties(Resources
				.getResource("translate.properties"));
		Map<String, String> translate = Maps.newHashMap();
		for (Entry<Object, Object> e : p.entrySet()) {
			String key = e.getKey() + "";
			String value = e.getValue() + "";
			translate.put(key, value);
		}
		return translate;
	}
}
