package cn.javaplus.plugins.generator.excel.generator;

public class Constent {
	public String[][]	constent;
	public String[]		types;
	public String[]		filedNames;
	public String		className;
	public String		explain;
	public String[]		marks;
	public String[]		explains;

	public Constent(String[] explains, String[] marks, String[][] constent, String[] types, String[] filedNames, String className) {
		this.constent = constent;
		this.types = types;
		this.filedNames = filedNames;
		this.className = className;
		this.explains = explains;
		this.marks = marks;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * 判断前端是否霦该类
	 *
	 * @return
	 */
	public boolean isClientNeed() {
		for (String s : marks) {
			if (!s.equals("1")) {
				return true;
			}
		}
		return false;
	}
}
