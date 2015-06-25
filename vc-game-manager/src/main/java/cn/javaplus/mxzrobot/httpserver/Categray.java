package cn.javaplus.mxzrobot.httpserver;

import java.util.List;

import cn.javaplus.string.StringPrinter;

public class Categray {

	private List<AskAction> childs;
	private String categray;

	public Categray(String categray, List<AskAction> childs) {
		this.categray = categray;
		this.childs = childs;
	}

	public String getChildsHtml() {
		StringPrinter out = new StringPrinter();
		for (AskAction a : childs) {
			out.print(a.getHtmlText());
		}
		return out.toString();
	}

	public String getCategray() {
		return categray;
	}

}
