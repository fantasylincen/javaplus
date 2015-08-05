package cn.javaplus.game.shhz;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Util;

import com.google.common.io.Resources;

/**
 * 模板
 * 
 * @author 林岑
 * 
 */
public class Templet {

	private Map<String, StringPrinter> replaces = new HashMap<String, StringPrinter>();
	private String templetText;

	/**
	 * @param tempPath
	 *            文件路径
	 */
	public Templet(String tempPath) {
		templetText = Util.File.getContent(Resources.getResource(tempPath));
	}

	public void writeToFile(String path) {
		String string = toString();
		Util.File.write(path, string);
		try {
			System.out.println(new File(path).getCanonicalPath());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {

		String temp = templetText;
		for (Entry<String, StringPrinter> e : replaces.entrySet()) {
			temp = temp.replaceAll(e.getKey(), e.getValue().toString());
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
