package cn.mxz.city;

import cn.mxz.Attribute;

public class AttributeRecorder {

	private City	city;
	private int	level;
	private Attribute	attribute;

	public AttributeRecorder(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public void save(int level, Attribute attribute) {
		this.level = level;
		this.attribute = attribute;
	}

	public Attribute getLastAttribute() {
		return attribute;
	}

	public int getLastLevel() {
		return level;
	}

}
