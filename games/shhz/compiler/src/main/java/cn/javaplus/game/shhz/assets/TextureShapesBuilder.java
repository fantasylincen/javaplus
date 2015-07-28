package cn.javaplus.game.shhz.assets;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TextureShapesBuilder {

	public String build() {
		Templet t = new Templet("TextureShapes.temp");
		JSONObject obj = JSON.parseObject(Util.File
				.getContent("../android/assets/texture_shapes.json"));
		JSONArray bodys = obj.getJSONArray("rigidBodies");
		for (Object o : bodys) {
			JSONObject jo = (JSONObject) o;
			t.append("FIELDS", buildField(jo));
		}
		return t.toString();
	}

	private String buildField(JSONObject jo) {
		JSONArray jr = jo.getJSONArray("shapes");
		String name = jo.getString("imagePath");
		name = new JavaNameConvent().getJavaName(name);

		Templet t = new Templet("TextureShapesField.temp");
		t.set("FIELD_NAME", name);

		JSONObject obj = jr.getJSONObject(0);
		JSONArray vertices = obj.getJSONArray("vertices");
		for (Object v : vertices) {
			JSONObject vertice = (JSONObject) v;
			Double x = vertice.getDouble("x");
			Double y = vertice.getDouble("y");
			String add = "				polygon.addPoint(" + x + ", " + y + ");";
			t.append("ADD_POINT", add);
		}
		return t.toString();
	}
}
