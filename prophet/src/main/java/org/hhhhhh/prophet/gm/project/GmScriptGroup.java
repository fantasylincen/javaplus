package org.hhhhhh.prophet.gm.project;

import java.util.List;

import org.dom4j.Element;
import org.hhhhhh.prophet.gen.dto.MongoGen.Lists;


@SuppressWarnings("unchecked")
public class GmScriptGroup  {
	private final Element group;
	private GmScriptGroup parent;

	public GmScriptGroup(Element group, GmScriptGroup parent) {
		this.group = group;
		this.parent = parent;
	}
	
	public String getName(){
		return group.elementTextTrim("name");
	}

	public List<GmScriptGroup> getChildrenGroup() {
		List<Element> elements = group.elements("group");
		List<GmScriptGroup> ls = Lists.newArrayList();
		for (Element element : elements) {
			ls.add(new GmScriptGroup(element, this));
		}
		return ls;
	}


	public List<GmScriptItem> getChildrenItem() {
		List<Element> elements = group.elements("item");
		List<GmScriptItem> ls = Lists.newArrayList();
		for (Element element : elements) {
			ls.add(new GmScriptItem(element, this));
		}
		return ls;
	}

	public GmScriptGroup getParent() {
		return parent;
	}


}
