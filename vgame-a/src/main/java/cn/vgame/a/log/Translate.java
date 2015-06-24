package cn.vgame.a.log;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.util.Util;

public class Translate {

	private static Map<String, String> tans;
	
	public static String translate(String log) {
		for (Entry<String, String> e : getTans().entrySet()) {
			String k = e.getKey();
			String v = e.getValue();
			
			log = log.replaceAll(k, v);
		}
		return log;
	}

	public static Map<String, String> getTans() {
		if(tans == null) {
//		System.out.println("xxxxxxxxxxxxxxxxx修改这里" );
			tans = Maps.newHashMap();
			Properties p = Util.Property.getProperties(Translate.class.getResource("translate.properties"));
			Set<Object> ks = p.keySet();
			for (Object kk : ks) {
				String key = kk.toString();
				String v = p.getProperty(key);
				tans.put(key, v);
			}
		}
		
		return tans;
	}

	public static void setTans(Map<String, String> tans) {
		Translate.tans = tans;
	}

}
