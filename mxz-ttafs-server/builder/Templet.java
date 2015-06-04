

import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Maps;

/**
 * 模板
 *
 * @author 林岑
 *
 */
public class Templet {

	private Map<String, StringPrinter>	replaces	= Maps.newHashMap();
	private String						templetText;

	public Templet(String templetText) {
		this.templetText = templetText;
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

	public void writeToFile(String path) {
		Util.File.write(path, toString());
	}
}
