package cn.javaplus.build.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.util.Util;

public class AsParser {

	public static final String FILE_NAME = "C:/Users/Administrator/git/games/shooter/core/src/cn/javaplus/shooter/game/PlayerBody.java";

	public static void main(String[] args) {
		String content = Util.File.getContent(FILE_NAME);

		// var polygonDef:b2PolygonDef;
		Pattern compile = Pattern.compile("var .+:b2.+;");
		while (true) {

			Matcher m = compile.matcher(content);
			if (!m.find()) {
				break;
			}
			String group = m.group();

			String name = group.replaceAll("var ", "").replaceAll(":.+", "");
			String type = group.replaceAll(".+:", "").replaceAll(";", "");

			content = m.replaceFirst(type + " " + name + ";");
		}

		content = content.replaceAll(
				"var arrBodyList:Array = new Array\\(\\);",
				"Map<String, b2Body> arrBodyList = new HashMap<String, b2Body>();");
//		content = content.replaceAll("b2", "");
//		content = content.replaceAll("PolygonDef", "Polygon");
//		content = content.replaceAll("CircleDef", "Circle");

		// arrBodyList["48207"] = newBody;
		compile = Pattern.compile("arrBodyList.+ = newBody;");
		while (true) {

			Matcher m = compile.matcher(content);
			if (!m.find()) {
				break;
			}
			String group = m.group();

			Pattern c = Pattern.compile("[0-9]{5}");
			Matcher mm = c.matcher(group);
			mm.find();
			String value = mm.group();

			content = m.replaceFirst("arrBodyList.put(\"" + value + "\""
					+ ",newBody);");
		}
//		arrBodyList["48207"]
		content = content.replaceAll("\'", "\"");

		compile = Pattern.compile("arrBodyList\\[\"[0-9]{5}\"\\]");
		while (true) {

			Matcher m = compile.matcher(content);
			if (!m.find()) {
				break;
			}
			String group = m.group();

			Pattern c = Pattern.compile("[0-9]{5}");
			Matcher mm = c.matcher(group);
			mm.find();
			String value = mm.group();

			content = m.replaceFirst("arrBodyList.get(\"" + value + "\")");
		}
		

		Util.File.write(FILE_NAME, content);
	}

}
