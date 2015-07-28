package cn.javaplus.crazy.base;

import java.util.Map;

public class Parameters {

	private Map<String, Object> parameters;

	public Parameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String getString(Object key) {
		Object object = parameters.get(key);
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	public Integer getInteger(Object key) {
		Object object = parameters.get(key);
		if (object == null) {
			return null;
		}
		return new Integer(object.toString());
	}

	@Override
	public String toString() {
		return parameters.toString();
	}
}
