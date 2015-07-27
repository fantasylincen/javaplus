package org.javaplus.clickscreen.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javaplus.clickscreen.excel.XmlSheetImpl.Field;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XmlRow implements Row {

	Map<String, String> map = new HashMap<String, String>();

	public XmlRow(List<Field> fields, Element row) {
		Array<Element> cells = row.getChildrenByName("Cell");
		for (int i = 0; i < cells.size; i++) {
			Field f = fields.get(i);
			Element e = cells.get(i);
			String value = Util.Xml.getData(e).getText();
			map.put(f.name, value);
		}
	}

	public void put(String name, String c) {
		map.put(name, c);
	}

	@Override
	public boolean getBool(Object name) {
		String v = get(name);
		if ("true".equals(v)) {
			return true;
		}
		if ("TRUE".equals(v)) {
			return true;
		}
		if ("1".equals(v)) {
			return true;
		}
		if ("T".equals(v)) {
			return true;
		}
		if ("t".equals(v)) {
			return true;
		}

		return false;
	}

	public String get(Object name) {
		return map.get(name);
	}

	@Override
	public int getInt(Object name) {
		String v = get(name);
		return new Integer(v);
	}

	@Override
	public double getDouble(Object name) {
		String v = get(name);
		return new Double(v);
	}

	@Override
	public float getFloat(Object name) {
		String v = get(name);
		return new Float(v);
	}

}
