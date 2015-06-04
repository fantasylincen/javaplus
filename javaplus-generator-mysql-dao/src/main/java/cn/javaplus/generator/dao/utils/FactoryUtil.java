package cn.javaplus.generator.dao.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import cn.javaplus.generator.dao.loader.PropertiesLoader;

import com.google.common.collect.Sets;

public class FactoryUtil {

	public static Set<String> getAllNeedToFactory() {
		Element DB = PropertiesLoader.getRoot();
		Element e = DB.element("generate_table");

		@SuppressWarnings("unchecked")
		List<Element> es = e.elements("table");
		HashSet<String> set = Sets.newHashSet();
		
		for (Element element : es) {
			set.add(element.attributeValue("name"));
		}
		return set;
	}

}
