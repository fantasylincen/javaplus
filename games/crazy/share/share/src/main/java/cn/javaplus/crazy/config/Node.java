package cn.javaplus.crazy.config;

import cn.javaplus.crazy.bucket.BaiduBucket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Node {

	private JSONObject json;
	private JSONObject root;

	public Node(JSONObject root, JSONObject child) {
		this.root = root;
		this.json = child;
	}

	public Integer getInt(String nodeName) {
		String v = getString(nodeName);
		if (v == null) {
			return null;
		}
		return new Integer(v);
	}

	public String getString(String name) {
		return json.getString(name);
	}

	public void commit(String bucket, String fileName) {
		BaiduBucket.put(bucket, fileName, root.toJSONString());
	}

	public void set(String k, Object v) {
		json.put(k, v);
	}

	public Node get(String string) {
		return new Node(root, json.getJSONObject(string));
	}

	public NodeArray getArray(String name) {
		JSONArray arr = json.getJSONArray(name);
		return new NodeArray(root, arr);
	}
}
