package org.javaplus.clickscreen.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XmlSheetImpl implements Sheet {

	public class Field {
		String type;
		String name;
	}

	private Element worksheet;
	private List<Row> all;
	private List<Field> fields;
	private Map<String, Row> map;

	public XmlSheetImpl(Element worksheet) {
		this.worksheet = worksheet;
		initFields();
		initAll();
		initMap();
	}

	private void initMap() {
		map = new HashMap<String, Row>();
		for (Row r : all) {
			String key = r.get(fields.get(0).name);
			map.put(key, r);
		}
	}

	private void initAll() {
		all = Lists.newArrayList();
		Array<Element> rows = worksheet.getChildByName("Table")
				.getChildrenByName("Row");

		for (int i = 3; i < rows.size; i++) {
			Element row = rows.get(i);
			all.add(new XmlRow(fields, row));
		}
	}

	private void initFields() {
		Array<Element> rows = worksheet.getChildByName("Table")
				.getChildrenByName("Row");
		Array<Element> types = rows.get(1).getChildrenByName("Cell");
		Array<Element> names = rows.get(2).getChildrenByName("Cell");
		fields = Lists.newArrayList();

		for (int i = 0; i < types.size; i++) {
			Field f = new Field();
			Element ne = names.get(i);
			Element te = types.get(i);
			String nt = Util.Xml.getData(ne).getText();
			Element dt = Util.Xml.getData(te);
//			if (dt == null)
//				throw new NullPointerException();
			String et = dt.getText();

			f.name = nt.trim();
			f.type = et.trim();
			fields.add(f);
		}
	}


	@Override
	public List<Row> getAll() {
		return all;
	}

	@Override
	public Row find(String fieldName, Object value) {
		for (Row r : all) {
			String s1 = r.get(fieldName).toString();
			String s2 = value.toString();
			if (s1.equals(s2)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public Row get(Object key) {
		String ss = key.toString();
		return map.get(ss);
	}

}
