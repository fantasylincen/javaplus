package cn.javaplus.generator;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.VariableDeclarator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.java.JavaFile;
import cn.javaplus.java.JavaFileImpl;
import cn.javaplus.string.String2;

import com.google.common.collect.Lists;

/**
 * 反向生成到EXCEL
 */
public class ConstGenerator2 {

	private static final String	HEAD	= "public static final ";
	private static final String	DOC		= "/\\*\\*.*\\*/\\s+";

	public static class Const {

		String	name;

		String	type;

		String	value;

		String	doc;

		public Const(String group) {

			doc = getDoc(group);

			group = group.replaceAll(DOC, "");
			group = group.replaceAll(HEAD, "");
			group = group.replaceAll(";", "");
			group = group.replaceAll("\\s+", " ");
			String[] split = group.split("=");
			value = split[1];
			split = split[0].split(" ");
			type = split[0];
			name = split[1];
		}

		private String getDoc(String group) {
			Pattern compile = Pattern.compile(DOC);
			Matcher matcher = compile.matcher(group);
			boolean find = matcher.find();
			if (find) {
				String g = matcher.group();
				g = g.replaceAll("/\\*\\*\\s+", "");
				g = g.replaceAll("\\*/", "");
				g = g.replaceAll("\\s+", " ");
				return g;
			}
			return "";
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public String getValue() {
			return value;
		}

		public String getDoc() {
			return doc;
		}
	}

	/**
	 * 打印所有的字段, 可直接覆盖到配置表中
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		String fileName = "D:\\workspace\\MobileServer\\gen\\define\\D.java";
		JavaFile file = new JavaFileImpl(fileName);
		List<BodyDeclaration> ms = file.getType().getMembers();
		for (BodyDeclaration m : ms) {
			if (m instanceof FieldDeclaration) {
				FieldDeclaration f = (FieldDeclaration) m;

				String2 comment = new String2(f.getComment());
				comment.delete("/\\*\\*");
				comment.delete("\\*/");
				comment.delete("\\*");
				comment.trim();

				VariableDeclarator v = f.getVariables().get(0);
				print(v.getId());
				print(f.getType());
				print(v.getInit());
				print(comment);
				System.out.println();
			}

			// new String2(m.get)

		}
		// String content = Util.File.getContent(fileName);
		//
		// List<Const> consts = getConsts(content);
		//
		// for (Const c : consts) {
		// print(c.getName());
		// print(c.getType());
		// print(c.getValue());
		// print(c.getDoc());
		// System.out.println();
		// }

	}

	private static List<Const> getConsts(String content) {
		List<Const> ls = Lists.newArrayList();
		Pattern compile = Pattern.compile(DOC + HEAD + ".*;");
		Matcher matcher = compile.matcher(content);
		while (matcher.find()) {
			String group = matcher.group();
			ls.add(new Const(group));
		}
		return ls;
	}

	private static void print(Object o) {
		System.out.print(o + "\t");
	}
}
