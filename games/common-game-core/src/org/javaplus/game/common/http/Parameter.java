package org.javaplus.game.common.http;

public class Parameter {

	String key;
	Object value;

	public Parameter(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public final String toString() {
		return key + "=" + value;
	}
}
