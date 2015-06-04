package cn.vgame.a.config;

import cn.vgame.a.Server;

import com.alibaba.fastjson.JSONObject;

public class ServerConfig {

	private JSONObject obj;

	public void init(JSONObject obj) {
		if(obj == null || obj.keySet().isEmpty()) {
			String identity = Server.getIdentity();
			throw new RuntimeException("server config not found, please set serverIdentity on system plantform, this server identity is " + identity);
		}
		this.obj = obj;
	}

	public String getString(String key) {
		return obj.getString(key);
	}

	public boolean getBoolean(String key) {
		return new Boolean(getString(key));
	}

	public double getDouble(String key) {
		return new Double(getString(key));
	}

	public int getInt(String key) {
		return new Integer(getString(key));
	}

}
