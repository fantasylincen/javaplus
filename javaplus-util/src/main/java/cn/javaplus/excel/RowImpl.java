package cn.javaplus.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;

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

	@Override
	public long getLong(Object name) {
		String v = get(name);
		return new Long(v);
	}

	@Override
	public String toString() {
		Fetcher<Entry<String, String>, Object> sss = new Fetcher<Map.Entry<String,String>, Object>() {
			
			@Override
			public Object get(Entry<String, String> t) {
				return t.getKey() + ":" + t.getValue();
			}
		};
		
		return Util.Collection.linkWith(",", map.entrySet(), sss);
	}
}
