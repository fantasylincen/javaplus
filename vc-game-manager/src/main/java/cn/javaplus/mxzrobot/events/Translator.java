package cn.javaplus.mxzrobot.events;

import java.util.HashSet;
import java.util.Set;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;

import com.google.common.collect.Sets;

public class Translator {

	
	/**
	 * 根据translate.properties翻译这句话
	 * @param result
	 * @return
	 */
	public String translate(String result) {
		IProperties p = Util.Property.getProperties("config/translate.properties");
		Set<Object> all = p.keySet();
		for (Object o : all) {
			String key = o.toString();
			if(result.equals(key.trim())) {
				return result;
			}
			String value = p.getProperty(key);
			Set<String> values = getValues(value);
			if(values.contains(result)) {
				return key;
			}
		}
		return result;
	}

	private Set<String> getValues(String value) {
		String[] vs = value.split("\\|\\|");
		HashSet<String> ss = Sets.newHashSet();
		
		for (String s : vs) {
			s = s.trim();
			if(s.isEmpty()) {
				continue;
			}
			ss.add(s);
		}
		
		return ss;
	}

}
