package cn.javaplus.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import cn.javaplus.log.Log;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public class XmlSheetImpl implements Sheet {

	public class Field {
		String type;
		String name;

		@Override
		public String toString() {
			return "sheet:" + sheetName + "    type:" + type + "    name:"
					+ name;
		}
	}

	private Element worksheet;
	private List<Row> all;
	private List<Field> fields;
	private Map<String, Row> map;
	private String sheetName;

	public XmlSheetImpl(Element worksheet) {
		this.worksheet = worksheet;
		sheetName = worksheet.attributeValue("Name");
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
		List<Element> rows = worksheet.element("Table").elements("Row");
		for (int i = 3; i < rows.size(); i++) {
			Element row = rows.get(i);
			XmlRow e = new XmlRow(fields, row);
			all.add(e);
			// Log.d(e);
		}
	}

	private void initFields() {
		List<Element> rows = worksheet.element("Table").elements("Row");
		List<Element> types = rows.get(1).elements("Cell");
		List<Element> names = rows.get(2).elements("Cell");
		fields = Lists.newArrayList();

		for (int i = 0; i < types.size(); i++) {
			Field f = new Field();
			Element ne = names.get(i);
			Element te = types.get(i);

			Element ned = XmlRow.getData(ne);

			String nt;
			if (ned != null)
				nt = ned.getText();
			else
				nt = "";

			Element dt = XmlRow.getData(te);

			String et;
			if (dt != null)
				et = dt.getText();
			else
				et = "";

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
	public Row findFirst(String fieldName, Object value) {
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

	@Override
	public List<Row> find(String fieldName, Object value) {
		ArrayList<Row> ls = Lists.newArrayList();
		for (Row r : all) {
			String s1 = r.get(fieldName).toString();
			String s2 = value.toString();
			if (s1.equals(s2)) {
				ls.add(r);
			}
		}
		return ls;
	}

}
