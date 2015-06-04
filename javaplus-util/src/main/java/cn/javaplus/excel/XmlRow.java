package cn.javaplus.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.google.common.collect.Lists;

import cn.javaplus.excel.XmlSheetImpl.Field;
import cn.javaplus.util.Util;

public class XmlRow implements Row {
	public static Element getData(Element ne) {
		return ne.element("Data");
	}

	Map<String, String> map = new HashMap<String, String>();

	private List<Element> trim(List<Element> rows) {
		ArrayList<Element> ls = Lists.newArrayList(rows);
		Iterator<Element> it = ls.iterator();
		while (it.hasNext()) {
			Element element = (Element) it.next();
			String textTrim = element.getTextTrim();
			if(textTrim.isEmpty())
				it.remove();
		}
		return ls;
	}
	@SuppressWarnings("unchecked")
	public XmlRow(List<Field> fields, Element row) {
		List<Element> cells = row.elements("Cell");
//		cells = trim(cells);
		
		if(fields.size() != cells.size())
			throw new RuntimeException("Cell's content is empty! " + fields.get(0));
		for (int i = 0; i < cells.size(); i++) {
			Field f = fields.get(i);
			Element e = cells.get(i);
			Element data = getData(e);

			String value;

			if (data != null) {
				value = data.getText();
			} else {
				value = "";
			}
			map.put(f.name, value);
			try {
				checkType(f, value);
			} catch (Exception e1) {
				throw new RuntimeException(e1.getMessage() + "  ---  " + f);
			}
		}
	}

	private void checkType(Field f, String value) {
		if (f.type.equals("Boolean")) {
			new Boolean(value);
		}
		if (f.type.equals("boolean")) {
			new Boolean(value);
		}
		if (f.type.equals("long")) {
			new Long(value);
		}
		if (f.type.equals("Long")) {
			new Long(value);
		}
		if (f.type.equals("Integer")) {
			new Integer(value);
		}
		if (f.type.equals("int")) {
			new Integer(value);
		}
		if (f.type.equals("String")) {
			new String(value);
		}
		if (f.type.equals("double")) {
			new Double(value);
		}
		if (f.type.equals("Double")) {
			new Double(value);
		}
		if (f.type.equals("float")) {
			new Float(value);
		}
		if (f.type.equals("Float")) {
			new Float(value);
		}
		if (f.type.equals("byte")) {
			new Byte(value);
		}
		if (f.type.equals("Byte")) {
			new Byte(value);
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
		Util.Exception.checkNull(v, name + " is null    values:" + map);
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

	@Override
	public String toString() {
		return map.toString();
	}
	@Override
	public long getLong(Object name) {
		String v = get(name);
		return new Long(v);
	}
}
