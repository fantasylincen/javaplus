package cn.javaplus.templet;

import java.net.URL;

import cn.javaplus.util.Util;

/**
 * 代码模板
 * 
 * @author 林岑
 */
public class CodeTemplet {

	private static final String MARK = "■■■";
	private String templet;

	private CodeTemplet(String templet) {
		this.templet = templet;
	}

	/**
	 * 在c的同级目录下, 取得 templetName的文本, 作为代码模板
	 * 
	 * @param c
	 *            在c的统计目录下获取资源
	 * @param templetName
	 * @return
	 */
	public static CodeTemplet create(Class<?> c, String templetName) {
		URL url = c.getResource(templetName);
		return new CodeTemplet(Util.File.getContent(url));
	}

	/**
	 * 替换模板中下一个 "■■■" 将其替换成 replace 如果没有下一个了, 将报错
	 * 
	 * @param replace
	 */
	public void setNext(Object replace) {
		if (!templet.contains(MARK)) {
			throw new RuntimeException();
		}
		templet = templet.replaceFirst(MARK, replace.toString());
	}

	public void setNext(int times, Object replace) {
		for (int i = 0; i < times; i++) {
			setNext(replace);
		}
	}

	@Override
	public String toString() {
		return templet;
	}

	public void tab(int tabCount) {
		for (int i = 0; i < tabCount; i++) {
			tab();
		}
	}

	public void tab() {
		String[] s = templet.split("\r");
		for (int i = 0; i < s.length; i++) {
			s[i] = "	" + s[i];
		}
		templet = Util.Collection.linkWith("\r", s);
	}
}
