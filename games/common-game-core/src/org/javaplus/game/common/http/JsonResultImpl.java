package org.javaplus.game.common.http;

import com.alibaba.fastjson.JSONObject;

public class JsonResultImpl implements JsonResult {

	private JSONObject object;

	public JsonResultImpl(JSONObject object) {
		this.object = object;
	}

	@Override
	public String getString(String key) {
		return object.getString(key);
	}

	@Override
	public JSONObject toJsonObject() {
		return object;
	}

	@Override
	public String toString() {
		return object.toJSONString();
	}
}
