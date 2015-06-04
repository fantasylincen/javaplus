package cn.mxz.chat;

import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class UnionChater implements Chater {

	public UnionChater(City city) {
	}

	@Override
	public void send(String message) {
	}

	public List<ChatMessage> getMessages() {
		return Lists.newArrayList();
	}

}
