package cn.javaplus.crazy.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class NodeArray {

	private JSONArray arr;
	private JSONObject root;

	public NodeArray(JSONObject root, JSONArray arr) {
		this.root = root;
		this.arr = arr;
	}

	public Node find(String fieldName, Object value) {
		for (Object obj : arr) {
			JSONObject jo = (JSONObject) obj;
			Object object = jo.get(fieldName);
			if (object.equals(value)) {
				return new Node(root, jo);
			}
		}
		return null;
	}

}
