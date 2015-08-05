package cn.javaplus.twolegs.bucket;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PropertiesImpl implements Properties {

	private java.util.Properties prop;

	public PropertiesImpl(java.util.Properties prop) {
		this.prop = prop;
	}

	@Override
	public int getInt(String key) {
		return new Integer(getString(key));
	}

	@Override
	public String getString(String key) {
		return prop.getProperty(key);
	}
	
	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		prop.list(out);
		out.close();
		return sw.toString();
	}

	@Override
	public void put(String key, String v) {
		prop.put(key, v);
	}

}
