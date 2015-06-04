package cn.javaplus.plugins.generator.protocol.config;

import _util.BaseTypeConfig;



public class FieldImpl implements IField {

	private String	type;
	private String	name;
	private String	doc;

	public FieldImpl(GeneratorConfig config) {
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDoc() {
		return doc;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDoc(String doc) {
		this.doc = doc;
	}

	@Override
	public String getPackagingType() {
		return BaseTypeConfig.parseToPackagingType(type);
	}

	@Override
	public String getASPackagingType() {

		String config = BaseTypeConfig.parseASType(type);

		if(config == null) {
			return getType();
		}

		return config;
	}

	@Override
	public String getTypeSimple() {
		String[] split = type.split("\\.");
		return split[split.length - 1];
	}

}
