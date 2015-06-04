package com.cnbizmedia.gm.project;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;

import com.cnbizmedia.config.CacheManager;
import com.cnbizmedia.gen.dto.MongoGen.Lists;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDto;

public class GmScriptManager {

	private final ProjectDto dto;

	public GmScriptManager(ProjectDto dto) {
		this.dto = dto;
	}

	public GmScriptGroup getScriptRoot() {
		Object key = key();

		GmScriptGroup root = (GmScriptGroup) CacheManager.get(key);

		if (root == null) {
			root = generateRoot(dto.getGmScript());
			CacheManager.put(key, Time.MILES_ONE_MIN * 2, root);
		}
		return root;
	}

	private Object key() {
		Object key = "gmScript:" + dto.getId();
		return key;
	}

	private GmScriptGroup generateRoot(String gmScript) {

		if (gmScript == null || gmScript.isEmpty())
			gmScript = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><group><name>root</name></group>";

		StringReader in = new StringReader(gmScript);

		SAXReader reader = new SAXReader();
		try {
			Document d = reader.read(in);
			Element root = d.getRootElement();
			GmScriptGroup rootG = new GmScriptGroup(root, null);

			return rootG;
		} catch (DocumentException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(in);
		}

	}

	private Map<String, GmScriptItem> getItems() {
		List<GmScriptItem> childs = getChilds(getScriptRoot());
		HashMap<String, GmScriptItem> mp = Maps.newHashMap();
		for (GmScriptItem gg : childs) {
			mp.put(gg.getId(), gg);
		}
		return mp;
	}

	private List<GmScriptItem> getChilds(GmScriptGroup g) {
		List<GmScriptItem> ls = Lists.newArrayList();

		ls.addAll(g.getChildrenItem());

		for (GmScriptGroup gg : g.getChildrenGroup()) {
			ls.addAll(getChilds(gg));
		}

		return ls;
	}

	public void clearCache() {
		Object key = key();
		CacheManager.put(key, 0, null);
	}

	public GmScriptItem getGmScript(String id) {
		Map<String, GmScriptItem> items = getItems();
		return items.get(id);
	}

}
