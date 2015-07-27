package org.javaplus.game.common.http;

import com.alibaba.fastjson.JSONObject;

public interface JsonResult {

	String getString(String key);

	JSONObject toJsonObject();

}
