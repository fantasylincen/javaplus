package cn.javaplus.file;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.log.Log;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Resources;
import cn.javaplus.util.Util;

/**
 * 模板
 * 
 * 例子:
 * 
 * public class CLASS_NAME { REPEATED FILEDS REPEATED METHODS }
 * 
 * Templet.set("CLASS_NAME", "Test");
 * 
 * @author 林岑
 * 
 */
public class Templet {

	private Map<String, StringPrinter> replaces = new HashMap<String, StringPrinter>();
	private String templetText;

	private Templet() {
	}

	/**
	 * @param tempPath
	 *            文件路径
	 */
	public Templet(String tempPath) {
		templetText = Util.File.getContent(Resources.getResource(tempPath));
	}

	public static final Templet createByFilePath(String tempPath) {
		Templet t = new Templet();
		t.templetText = Util.File.getContent(Resources.getResource(tempPath));
		return t;
	}

	public static final Templet createByContent(String content) {
		Templet t = new Templet();
		t.templetText = content;
		return t;
	}

	public void writeToFile(String path) {
		String string = toString();
		Util.File.write(path, string);
	}
	@Override
	public String toString() {

		String temp = templetText;
		for (Entry<String, StringPrinter> e : replaces.entrySet()) {
			String key = e.getKey();
			StringPrinter value = e.getValue();
			
			try {
				String str = value.toString();
				str = str.replaceAll("\\$", "\\\\\\$");
				
				temp = temp.replaceAll(key, str);

			} catch (Exception e1) {
				Log.e(key, value);
				throw new RuntimeException(e1);
			}
		}
		return temp;
	}

	/**
	 * @param regex
	 *            将要替换的字符串
	 * @param replaceString
	 *            ...
	 */
	public void set(String regex, String replaceString) {
		replaces.put(regex, new StringPrinter(replaceString));
	}

	/**
	 * 对 REPEATED 指定的 替换对象, 追加一个文本
	 * 
	 * @param regex
	 * @param replaceString
	 */
	public void append(String regex, String replaceString) {

		regex = "REPEATED " + regex;

		StringPrinter out = replaces.get(regex);

		if (out == null) {
			out = new StringPrinter();
			replaces.put(regex, out);
		}

		out.println(replaceString);
	}
}
