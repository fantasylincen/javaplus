package cn.javaplus.crazy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.javaplus.crazy.bucket.BaiduBucket;

public class ServerJson {

	public static Node getRoot() {
		String configJson = BaiduBucket.getText("crazy-pocker", "config.json");
		JSONObject parseObject = JSON.parseObject(configJson);
		Node root = new Node(parseObject, parseObject);
		return root;
	}
}
