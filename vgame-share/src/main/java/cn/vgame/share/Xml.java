package cn.vgame.share;

import cn.javaplus.excel.Sheet;
import cn.javaplus.excel.StaticData;
import cn.javaplus.excel.XmlDataImpl;

public class Xml implements StaticData {

	private XmlDataImpl staticData;

	private static Xml xml;

	@Override
	public Sheet get(String name) {
		return staticData.get(name);
	}

	public void init(String content) {
		staticData = new XmlDataImpl(content);
	}

	public static Xml getXml() {
		if (xml == null) {
			xml = new Xml();
		}
		return xml;
	}

	public static Sheet getSheet(String sheetName) {
		return getXml().get(sheetName);
	}

	private Xml() {
	}
}
