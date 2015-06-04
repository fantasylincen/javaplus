package cn.vgame.b.config;

import cn.vgame.b.Server;

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
		String kk = obj.getString(key);
		if(kk == null)
			throw new NullPointerException(key + "'s value not found");
			
		return kk;
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
