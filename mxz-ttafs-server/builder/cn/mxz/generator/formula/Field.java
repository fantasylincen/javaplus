package cn.mxz.generator.formula;

/**
 * 字段
 *
 * @author 林岑
 *
 */
public class Field {
	String	comment;
	String	type;
	private int	index;

	public Field(String type, String comment, int index) {
		this.type = type;
		this.comment = comment;
		this.index = index;
	}

	public String getComment() {
		return comment;
	}

	public String getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return "a" + index;
	}

}
