//package cn.javaplus.mongodao.generator;
//
//import java.util.Map;
//import java.util.Map.Entry;
//
//import cn.javaplus.log.Log;
//import cn.javaplus.string.StringPrinter;
//
//import com.google.common.collect.Maps;
//
///**
// * 模板
// *
// * @author 林岑
// *
// */
//public class Templet {
//
//	private Map<String, StringPrinter>	replaces	= Maps.newHashMap();
//	private String						templetText;
//
//	public Templet(String templetText) {
//		this.templetText = templetText;
//	}
//
//	@Override
//	public String toString() {
//
//		String temp = templetText;
//		for (Entry<String, StringPrinter> e : replaces.entrySet()) {
//			String key = e.getKey();
//			StringPrinter value = e.getValue();
//			
//			try {
//				String str = value.toString();
//				str = str.replaceAll("\\$", "\\\\\\$");
//				temp = temp.replaceAll(key, str);
//			} catch (Exception e1) {
//				Log.e(key, value);
//				throw new RuntimeException(e1);
//			}
//		}
//		return temp;
//	}
//
//	/**
//	 * @param regex
//	 *            将要替换的字符串
//	 * @param replaceString
//	 *            ...
//	 */
//	public void set(String regex, String replaceString) {
//		replaces.put(regex, new StringPrinter(replaceString));
//	}
//
//	/**
//	 * 对 REPEATED 指定的 替换对象, 追加一个文本
//	 * @param regex
//	 * @param replaceString
//	 */
//	public void append(String regex, String replaceString) {
//
//		regex = "REPEATED " + regex;
//
//		StringPrinter out = replaces.get(regex);
//
//		if (out == null) {
//			out = new StringPrinter();
//			replaces.put(regex, out);
//		}
//
//		out.println(replaceString);
//	}
//}
