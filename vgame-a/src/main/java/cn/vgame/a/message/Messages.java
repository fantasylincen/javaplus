package cn.vgame.a.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.vgame.a.Server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Messages {

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String userMessages;

	public Messages(String userMessages) {
		this.userMessages = userMessages;
	}

	public List<Message> getMessages() {
		ArrayList<Message> ls = Lists.newArrayList();
		JSONArray arr = JSON.parseArray(userMessages);
		for (Object text : arr) {
			JSONObject obj = JSON.parseObject(text.toString());
			ls.add(new Message(obj));
		}
		return ls;
	}

	public String toJsonString() {
		List<Message> messages = getMessages();	
		return JSON.toJSONString(messages);
	}

	public void addMessage(Message m) {
		List<Message> messages = getMessages();	
		messages.add(m);
		sort(messages);
		if(messages.size() > Server.getConst().getInt("MAX_MESSAGE_COUNT")) {
			messages.remove(0);
		}
		userMessages = JSON.toJSONString(messages);
	}

	private void sort(List<Message> messages) {
		Comparator<Message> com = new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				return (int) (o1.getTime() - o2.getTime());
			}
		};
		Collections.sort(messages, com);
	}


}
