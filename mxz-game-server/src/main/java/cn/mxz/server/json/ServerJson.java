package cn.mxz.server.json;

import cn.mxz.server.bucket.BaiduBucket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ServerJson {

	public static Node getRoot() {
		String configJson = BaiduBucket.getText("game-configs", "game1001.json");
		JSONObject parseObject = JSON.parseObject(configJson);
		Node root = new Node(parseObject, parseObject);
		return root;
	}
}
