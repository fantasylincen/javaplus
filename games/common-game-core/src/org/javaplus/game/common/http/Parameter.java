package org.javaplus.game.common.http;

public class Parameter {

	String key;
	String value;

	public Parameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public final String toString() {
		return key + "=" + value;
	}
}
