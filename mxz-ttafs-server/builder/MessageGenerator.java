import java.text.MessageFormat;

import cn.javaplus.util.Util;
import cn.mxz.generator.MessageTempletConfig;

/**
 * 将后端的文字提示信息转换成XML配置表
 * 
 * @author 林岑
 * @time 2012年9月18日 11:27:38
 * 
 */
public class MessageGenerator {

	public static void generate() {

		// package message;
		//
		// public class S {
		//
		// REPEATED FILEDS
		//
		// public static class Str {
		//
		// REPEATED STRS
		// }
		// }
		//

		try {
			Templet t = new Templet(Util.File.getContent("res/build/S.temp"));

			MessageTempletConfig.load();

			for (Integer k : MessageTempletConfig.getKeys()) {

				String v = MessageTempletConfig.get(k).getContent();

				String key = k + "";
				
				String ss1 = "	/** {0} */\r	public static final int S{1} = {2};\r\r";
				String s1 = MessageFormat.format(ss1, v, key, key);
				
				String ss2 = "	/** {0} */\r	public static final String STR{1} = \"{2}\";\r\r";
				String s2 = MessageFormat.format(ss2, v, key, v.replaceAll("\\\"", "\\\\\\\\\""));

				t.append("FILEDS", s1);
				t.append("STRS", s2);
			}
			t.writeToFile("gen/message/S.java");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
