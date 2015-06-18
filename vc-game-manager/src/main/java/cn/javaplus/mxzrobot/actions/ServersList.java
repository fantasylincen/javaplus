package cn.javaplus.mxzrobot.actions;

import java.util.Set;

import cn.javaplus.file.IProperties;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class ServersList extends ActionBase {

	public String execute(Args args) {
		IProperties p = Util.Property.getProperties("config/translate.properties");

		Set<Object> ks = p.keySet();

		StringPrinter out = new StringPrinter();
		for (Object key : ks) {
			String object = p.getProperty(key.toString());
			out.print(object);
			out.print("&nbsp;&nbsp;:&nbsp;&nbsp;");
			out.print(key);
			out.print("<br>");
		}
		return out.toString();
	}

}
