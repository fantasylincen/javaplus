package cn.javaplus.generator.dao.dto;

public class Column {

	private String	name;
	private int		type;
	private String	typename;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypename() {
		return typename;
	}

	@Override
	public String toString() {
		return "name = " + name + " type = " + type + " typename = " + typename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + type;
		result = prime * result + ((typename == null) ? 0 : typename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Column) {
			Column c = (Column) o;
			return type == c.type && name.equals(c.name) && typename.equals(c.typename);

		} else {
			return false;
		}
	}
}
