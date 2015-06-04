package cn.javaplus.mongodao.generator;

public class Annotation {

	private String value;
	private String name;

	public Annotation(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

}
