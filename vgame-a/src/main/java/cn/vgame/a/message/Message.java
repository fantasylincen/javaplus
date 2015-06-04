package cn.vgame.a.message;

import java.util.Date;

import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSONObject;

public class Message {

	private final JSONObject obj;

	public Message(JSONObject obj) {
		this.obj = obj;
	}

	public Message(String nick, String message) {
		obj = new JSONObject();
		long time = System.currentTimeMillis();
		Date date = new Date(time);

		obj.put("message", message);
		obj.put("id", Util.ID.createId());
		obj.put("date", Messages.format.format(date));
		obj.put("time", time);
		obj.put("nick", nick);
	}

	public String getMessage() {
		return obj.getString("message");
	}

	public String getId() {
		return obj.getString("id");
	}
	public String getNick() {
		return obj.getString("nick");
	}

	public String getDate() {
		return obj.getString("date");
	}
	
	public long getTime() {
		return obj.getLongValue("time");
	}
}