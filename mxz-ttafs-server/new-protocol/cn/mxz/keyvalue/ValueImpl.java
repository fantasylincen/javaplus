package cn.mxz.keyvalue;

public class ValueImpl implements Value {

	private String value;

	public ValueImpl(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
