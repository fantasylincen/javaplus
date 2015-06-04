package cn.javaplus.generator.loader;

import java.io.File;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PropertiesLoader {

	private static String PATH = "res/build/const_generator.xml";


	private static Document document;

	public static void reloadDoc() {
		File inputXml = new File(PATH);
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(inputXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Document getDoc() {
		if(document == null) {
			reloadDoc();
		}
		return document;
	}

	public static Element getRoot() {
		return getDoc().getRootElement();
	}

	public static String getDBPropertiesFileName() {
		Document d = getDoc();
		Element root = d.getRootElement();
		Attribute attribute = root.element("DB").attribute("path");
		return attribute.getValue();
	}

	/** 获得被监听的 Excel 数值目录 */
	public static String getExcelPropertiesFileName() {
		Document d = getDoc();
		Element root = d.getRootElement();
		Attribute attribute = root.element("EXCEL").attribute("path");
		return attribute.getValue();
	}

	/** Excel 模板路径 */
	public static String getTempletsPath() {
		Document d = getDoc();
		Element root = d.getRootElement();
		Attribute attribute = root.element("EXCEL").attribute("templets");
		return attribute.getValue();
	}
}
