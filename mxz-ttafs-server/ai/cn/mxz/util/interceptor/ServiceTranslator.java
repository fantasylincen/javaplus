package cn.mxz.util.interceptor;

import java.util.Set;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;
import cn.mxz.util.debuger.Debuger;

public class ServiceTranslator {

	private static IProperties p;
	
	public ServiceTranslator() {
		if(p == null) {
			p = Util.Property.getProperties("res/build/translate.properties");
		}
	}
	
	public String translate(String explain) {
		if(explain == null) {
			Debuger.error("ServiceTranslator.translate() 1 未找到" + explain + "的说明!");
			return "other";
		}
		String property = p.getProperty(explain);
		if(property == null) {
			Debuger.error("ServiceTranslator.translate() 2 未找到" + explain + "的说明!");
			return explain;
		}
		return property;
	}
	
	public static void main(String[] args) {
		Set<Object> all = p.keySet();
		for (Object o : all) {
			System.out.println(p.get(o));
		}
	}

}
