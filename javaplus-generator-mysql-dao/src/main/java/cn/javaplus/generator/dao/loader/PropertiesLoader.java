package cn.javaplus.generator.dao.loader;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PropertiesLoader {

	private static String PATH = "src/main/resources/generator.xml";

	private static Document document;

	/** Excel 模板路径 */
	public static String getTempletsPath() {
		Element root = getRoot().element("templesPath");
		return root.getTextTrim();
	}

	private static Document getDoc() {
		if(document == null) {
			File inputXml = new File(PATH);
			SAXReader saxReader = new SAXReader();
			try {
				document = saxReader.read(inputXml);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return document;
	}

	public static Element getRoot() {
		return getDoc().getRootElement();
	}
}
