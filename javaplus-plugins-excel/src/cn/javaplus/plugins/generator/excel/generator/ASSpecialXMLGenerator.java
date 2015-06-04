package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class ASSpecialXMLGenerator extends AbstractGenerator {

	private static FileUtils	fu	= new FileUtils();

	public ASSpecialXMLGenerator() {
		super("baseClassConfig.txt");
	}

	public void generate(Constent c) {
		StringBuilder s = new StringBuilder("");

		toForm(s, c);// 固定格式

		for (int i = 0; i < 1024; i++) {
			s.append(" ");
		}

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String value = store.getString(D.Paths.AS_XML + "") + File.separator + c.className + "Config" + ".xml";
		fu.write(value, s.toString());
	}

	public StringBuilder testGenerate(Constent c) {
		StringBuilder s = new StringBuilder("");

		toForm(s, c);// 固定格式

		return s;
	}

	private static final String[]	NEED_TO_BE_FILES	= new String[] { "storyId", "type", "url" };

	/**
	 * 转换配置表
	 * 
	 * @param sb
	 * @param c
	 * @return
	 */
	public static void toForm(StringBuilder sb, Constent c) {
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r<root>\r\r");
		sb.append("<!-- " + c.explain + " -->\r\r");
		sb.append("		<classPath>cn.mxz.vo.DramaVo</classPath>\r");
		sb.append(" 	<config prefix=\"res/images/" + c.className.toLowerCase() + "/\" pictype=\".jpg\" index=\"storyId\"/>\r\r");

		String item = "";// 记录item是否变化

		String[][] constent = c.constent;// 每个元素
		String[] filedNames = c.filedNames;// 每个属性名

		for (int i = 0; i < constent.length; i++) {// 一行

			String[] values = c.constent[i];
			String indexValue = values[1];

			if (!item.equals(indexValue) && !item.equals(""))// </item>
				sb.append("</item>\r\r");
			if (!item.equals(indexValue)) {// item是否相等 <item id="5">
				String fileds = getFileds(values, filedNames);
				sb.append("<item FILEDS>\r".replaceAll("FILEDS", fileds));
			}
			item = indexValue;

			sb.append(" 	<dialog");

			for (int j = 2; j < values.length; j++) {// 重第二个开始

				String name = filedNames[j];
				if (isNeedToBeFiled(name)) {
					continue;
				}
				String value = values[j];

				addElement(sb, name, value);
			}
			sb.append(">");

			String contentValue = values[values.length - 1];

			addContent(sb, contentValue);

			sb.append("</dialog>\r");

		}
		sb.append("</item>\r");
		sb.append("\r</root>");

		// Printer.println(sb.toString());
	}

	private static String getFileds(String[] values, String[] filedNames) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < filedNames.length; i++) {
			if (isNeedToBeFiled(filedNames[i])) {
				sb.append(filedNames[i] + "=\"" + values[i] + "\" ");
			}
		}
		return sb.toString();
	}

	private static boolean isNeedToBeFiled(String string) {
		for (String s : NEED_TO_BE_FILES) {
			if (string.equals(s)) {
				return true;
			}
		}
		return false;
	}

	private static void addElement(StringBuilder sb, String filedName, String value) {
		if (filedName.equals("content")) {
			sb.append(" " + filedName + "=\"\"");
		} else {
			sb.append(" " + filedName + "=\"" + value + "\"");
		}
	}

	private static void addContent(StringBuilder sb, String value) {
		sb.append(value);
	}
}
