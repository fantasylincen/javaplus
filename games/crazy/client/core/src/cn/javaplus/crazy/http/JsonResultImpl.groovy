package cn.javaplus.crazy.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.Net.HttpResponse;

public class JsonResultImpl implements JsonResult {

	private JSONObject object;

	public JsonResultImpl(HttpResponse result) {
		String text = null;
		try {
			text = result.getResultAsString();
			object = JSON.parseObject(text);
		} catch (JSONException e) {
			throw new RuntimeException("非法字符串:" + text);
		}

	}

	@Override
	public String getString(String key) {
		return object.getString(key);
	}

}
