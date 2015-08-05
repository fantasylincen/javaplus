package cn.javaplus.twolegs.base;

import com.alibaba.fastjson.JSONObject;

public class Response {

	private JSONObject response;

	public Response(JSONObject response) {
		this.response = response;
	}

	public Integer getInteger(String key) {
		return response.getInteger(key);
	}

	public String getString(String key) {
		return response.getString(key);
	}

	public Object put(String key, Object value) {
		return response.put(key, value);
	}
}
