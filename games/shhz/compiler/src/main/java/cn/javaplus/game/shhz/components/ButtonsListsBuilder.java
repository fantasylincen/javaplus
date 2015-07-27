package cn.javaplus.game.shhz.components;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Resources;

public class ButtonsListsBuilder {

	public String build() {
		Templet temp = new Templet("ButtonLists.temp");
		JSONArray obj = JSON.parseArray(Util.File.getContent(Resources
				.getResource("components/ButtonLists.od")));
		for (Object o : obj) {
			JSONObject buttonList = (JSONObject) o;
			temp.append("CREATORS", buildCreators(buttonList));
			temp.append("BUTTON_IDS", buildButtonIds(buttonList));
			temp.append("BUTTON_LIST_CLASSES", buildButtonListClass(buttonList));
		}
		return temp.toString();
	}

	private String buildButtonIds(JSONObject buttonList) {
		String name = buttonList.getString("name");

		JSONArray bs = buttonList.getJSONArray("Buttons");
		StringPrinter sp = new StringPrinter();
		for (Object o : bs) {
			JSONObject obj = (JSONObject) o;
			String n2 = obj.getString("name");
			String c = name + n2;
			sp.println("		public static final String " + c + " = \"" + c
					+ "\";");
		}
		return sp.toString();
	}

	private String buildButtonListClass(JSONObject buttonList) {
		Integer x = buttonList.getInteger("x");
		Integer y = buttonList.getInteger("y");
		JSONArray bs = buttonList.getJSONArray("Buttons");
		Templet temp = new Templet("ButtonList.temp");
		String name = buttonList.getString("name");
		temp.set("CLASS_NAME", name);
		temp.set("SET_X", x + "");
		temp.set("SET_Y", y + "");
		for (Object o : bs) {
			JSONObject obj = (JSONObject) o;
			temp.append("ADD_BUTTONS", buildAddButtons(buttonList, obj));
		}
		return temp.toString();
	}

	private String buildAddButtons(JSONObject buttonList, JSONObject obj) {

		// addButton(ButtonLists.MainButtonListExit, 500, 5, "pathbutton.png");
		Integer x = obj.getInteger("x");
		Integer y = obj.getInteger("y");
		Integer w = obj.getInteger("w");
		Integer h = obj.getInteger("h");
		String name = "ButtonLists." + buttonList.getString("name")
				+ obj.getString("name");
		String image = obj.getString("image");

		return "				addButton(" + name + ", " + x + ", " + y + ", " + w + ", "
				+ h + ", \"" + image + "\");";
	}

	private String buildCreators(JSONObject buttonList) {
		String name = buttonList.getString("name");

		StringPrinter sp = new StringPrinter();
		sp.println("		public static ButtonList create" + name + " () {");
		sp.println("			return new " + name + "();");
		sp.println("		}");
		return sp.toString();
	}

}
