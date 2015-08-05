package cn.javaplus.shhz.util;

import java.util.List;

import cn.javaplus.shhz.collections.Lists;

public class StringResolver {

	private String s;

	public StringResolver(String s) {
		this.s = s;
	}

	/**
	 * 按分隔符 sp 拆分字符串
	 * 
	 * @param sp
	 * @return
	 */
	public List<StringResolver> split(String sp) {
		List<StringResolver> ls = Lists.newArrayList();
		String[] split = s.split(sp);
		for (String s : split) {
			ls.add(new StringResolver(s));
		}
		return ls;
	}

	public int getInt() {
		return new Integer(s);
	}

	public double getDouble() {
		return new Double(s);
	}

	public long getLong() {
		return new Long(s);
	}

	public float getFloat() {
		return new Float(s);
	}

	public String getString() {
		return s;
	}

	public boolean getBoolean() {
		return new Boolean(s);
	}

	@Override
	public String toString() {
		return s;
	}

}
