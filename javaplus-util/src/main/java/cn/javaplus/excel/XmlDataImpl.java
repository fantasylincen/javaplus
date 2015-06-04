package cn.javaplus.excel;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.util.Closer;

@SuppressWarnings("unchecked")
public class XmlDataImpl implements StaticData {

	public XmlDataImpl(String content) {

		StringReader sr = new StringReader(content);
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(sr);
			Element root = document.getRootElement();
			List<Element> elements = root.elements("Worksheet");

			for (Element s : elements) {
				String attribute = s.attributeValue("Name");
				map.put(attribute, new XmlSheetImpl(s));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(sr);
		}
	}

	private Map<String, Sheet> map = new HashMap<String, Sheet>();

	@Override
	public Sheet get(String name) {
		Sheet value = map.get(name);
		return value;
	}

}
