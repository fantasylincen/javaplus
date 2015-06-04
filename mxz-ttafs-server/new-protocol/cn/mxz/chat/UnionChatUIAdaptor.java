package cn.mxz.chat;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class UnionChatUIAdaptor implements ChatUI {

	private City user;
	private UnionChater chater;

	public UnionChatUIAdaptor(City user) {
		this.user = user;
		chater = user.getUnionChater();
	}

	@Override
	public List<ChatMessageUI> getMessages() {
		List<ChatMessage> messages = chater.getMessages();
		ArrayList<ChatMessageUI> ls = Lists.newArrayList();
		for (ChatMessage chatMessage : messages) {
			ls.add(new ChatMessageUIAdaptor(user, chatMessage));
		}
		return ls;
	}


}
