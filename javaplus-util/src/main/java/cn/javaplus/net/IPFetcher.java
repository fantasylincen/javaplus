package cn.javaplus.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.web.WebContentFethcer;

public class IPFetcher {

	public static String getIP() {
//		IP地址：<input type="text"  name="ip" value="125.84.251.167"> <input type="submit">
		String string;
		try {
			Pattern c = Pattern.compile("IP地址：<input type=\"text\"  name=\"ip\" value=\".+> <input type=\"submit\">");
			string = WebContentFethcer.get("gb2312",
					"http://whois.pconline.com.cn");
			Matcher m = c.matcher(string);
			m.find();
			String group = m.group();
			Pattern c2 = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
			Matcher mm = c2.matcher(group);
			mm.find();
			group = mm.group();
			return group;
		} catch (Exception e) {
			return "0.0.0.0";
		}
	}

}
