package cn.javaplus.twolegs.json;

import cn.javaplus.twolegs.bucket.BaiduBucket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ServerJson {

	public static Node getRoot() {
		String configJson = BaiduBucket.getText("two-legs", "server.json");
		JSONObject parseObject = JSON.parseObject(configJson);
		Node root = new Node(parseObject, parseObject);
		return root;
	}
}
