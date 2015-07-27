package org.javaplus.clickscreen.excel;

import java.util.HashMap;
import java.util.Map;

public class RowImpl implements Row {

	Map<String, String> map = new HashMap<String, String>();

	public void put(String name, String c) {
		map.put(name, c);
	}

	@Override
	public boolean getBool(Object name) {
		String v = get(name);
		if("true".equals(v)) {
			return true;
		}
		if("TRUE".equals(v)) {
			return true;
		}
		if("1".equals(v)) {
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
