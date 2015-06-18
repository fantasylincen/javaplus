package cn.javaplus.mxzrobot.events;

import java.util.Map;
import java.util.Set;

import cn.javaplus.mxzrobot.actions.Args;

public class ArgsImpl implements Args {

	private Map<String, String> result;

	public ArgsImpl(Map<String, String> result) {
		this.result = result;
		translate();
	}

	/**
	 * 翻译
	 */
	private void translate() {
		for (String key : result.keySet()) {
			String r = result.get(key);
			r = new Translator().translate(r);
			result.put(key, r);
		}
	}

	public int getInt(Object key) {
		String o = result.get(key);
		return new Integer(o);
	}

	public String getString(Object key) {
		String o = result.get(key);
		return o;
	}

	public double getDouble(Object key) {
		String o = result.get(key);
		return new Double(o);
	}

	public long getLong(Object key) {
		String o = result.get(key);
		return new Long(o);
	}

	public boolean getBoolean(Object key) {
		String o = result.get(key);
		return new Boolean(o);
	}

	public Set<String> getKeys() {
		return result.keySet();
	}

	@Override
	public String toString() {
		return result.toString();
	}
}
