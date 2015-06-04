package com.cnbizmedia.gm.project;

import org.dom4j.Element;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

public class GmScriptItem {

	private final Element item;
	private final GmScriptGroup parent;

	public GmScriptItem(Element item, GmScriptGroup parent) {
		this.item = item;
		this.parent = parent;
	}

	public String getName() {
		return item.elementTextTrim("name");
	}

	public String getId() {
		String unicode = Util.Str.toUnicode(getName());
		StringBuilder sb = new StringBuilder(unicode);
		GmScriptGroup p = getParent();
		while (true) {
			if (p == null) {
				break;
			}
			unicode = Util.Str.toUnicode(p.getName());

			unicode = unicode.replaceAll("\\\\u", "").replaceAll("\\\\", "");

			sb.append(unicode);
			p = p.getParent();
		}
		return sb.toString();
	}

	public GmScriptGroup getParent() {
		return parent;
	}

	public String getCommitPage() {
		return item.elementText("commitPage");
	}

}
