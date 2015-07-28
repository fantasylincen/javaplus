package cn.javaplus.twolegs.json;

import cn.javaplus.twolegs.bucket.BaiduBucket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Node {

	private JSONObject child;
	private JSONObject root;

	public Node(JSONObject root, JSONObject child) {
		this.root = root;
		this.child = child;
	}

	public Integer getInt(String nodeName) {
		String v = getString(nodeName);
		if (v == null) {
			return null;
		}
		return new Integer(v);
	}

	public String getString(String name) {
		return child.getString(name);
	}

	public void commit(String bucket, String fileName) {
		BaiduBucket.put(bucket, fileName, root.toJSONString());
	}

	public void set(String k, Object v) {
		child.put(k, v);
	}

	public Node get(String string) {
		JSONObject jsonObject = child.getJSONObject(string);
		return new Node(root, jsonObject);
	}

	public NodeArray getArray(String name) {
		JSONArray arr = child.getJSONArray(name);
		return new NodeArray(root, arr);
	}
}
