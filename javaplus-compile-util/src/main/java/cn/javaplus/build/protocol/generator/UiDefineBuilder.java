package cn.javaplus.build.protocol.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.javaplus.file.Templet;
import cn.javaplus.templet.CodeTemplet;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UiDefineBuilder {

	public class Node {

		private JSONObject obj;

		public Node(JSONObject obj) {
			this.obj = obj;
		}

		public String getName() {
			return obj.getJSONObject("options").getString("name");
		}

		public String getType() {
			return obj.getJSONObject("options").getString("classname");
		}

		public List<Node> getChildrens() {
			JSONArray arr = obj.getJSONArray("children");
			if (arr == null) {
				return Lists.newArrayList();
			}

			ArrayList<Node> ls = Lists.newArrayList();
			for (Object object : arr) {
				ls.add(new Node((JSONObject) object));
			}
			return ls;
		}

		public String getClassname() {
			return obj.getJSONObject("options").getString("classname");
		}

	}

	public String generate() {
		Templet t = Templet.createByFilePath("UiDefineBuilder.Uis");

		File file = new File("../../client/ui/Json");
		File[] uis = file.listFiles();
		for (File ui : uis) {
			t.append("CLASSES", buildCocosClass(ui));
		}

		return t.toString();
	}

	private String buildCocosClass(File ui) {

		String jsonName = ui.getName();
		String uiName = jsonName.split("\\.")[0];
		String content = Util.File.getContent(ui);
		JSONObject obj = JSON.parseObject(content);

		Node node = new Node(obj.getJSONObject("widgetTree"));

		CodeTemplet t = CodeTemplet.create(UiDefineBuilder.class, "templet01");
		CodeTemplet f = build(node);

		t.setNext(uiName);
		t.setNext(f.toString());
		t.setNext(jsonName);
		t.setNext(6, uiName);
		t.tab(2);
		return t.toString();
	}

	private CodeTemplet build(Node node) {

		String name = node.getName();

		String hump = Util.Str.hump(name);
		hump = Util.Str.firstToUpperCase(hump);

		String c = node.getClassname();

		String type = parse(c);

		if (type.equals("Group")) {
			CodeTemplet t = CodeTemplet.create(UiDefineBuilder.class,
					"templet03");
			t.setNext(2, hump);

			List<Node> childrens = node.getChildrens();

			StringBuilder sb = new StringBuilder();
			for (Node n : childrens) {
				CodeTemplet child = build(n);
				child.tab();
				sb.append(child + "\r");
			}
			t.setNext(sb);
			t.setNext(3, hump);
			return t;
		} else {
			CodeTemplet t = CodeTemplet.create(UiDefineBuilder.class,
					"templet02");
			t.setNext(type);
			t.setNext(hump);
			t.setNext(type);
			t.setNext(name);
			return t;
		}

	}

	private String parse(String c) {
		Map<String, String> m = Maps.newHashMap();

		m.put("LabelBMFont", "Label");
		m.put("TextField", "TextField");
		m.put("Button", "ImageButton");
		m.put("Panel", "Group");
		m.put("CustomImageView", "Image");
		m.put("ImageView", "Image");
		m.put("ScrollPane", "ScrollPane");
		m.put("CheckBox", "CheckBox");
		m.put("LoadingBar", "Image");
		m.put("LabelAtlas", "LabelAtlas");
		String string = m.get(c);
		if (string == null) {
			return "Actor";
		}
		return string;
	}

}
