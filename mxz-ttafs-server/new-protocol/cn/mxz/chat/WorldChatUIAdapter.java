package cn.mxz.chat;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class WorldChatUIAdapter implements ChatUI {

	private City user;
	private WorldChater chater;

	public WorldChatUIAdapter(City user) {
		this.user = user;
		chater = user.getWorldChater();
	}

	@Override
	public List<ChatMessageUI> getMessages() {
		List<WorldChatMessage> messages = chater.getMessages();
		ArrayList<ChatMessageUI> ls = Lists.newArrayList();
		for (ChatMessage chatMessage : messages) {
			ls.add(new ChatMessageUIAdaptor(user, chatMessage));
		}
		return ls;
	}

}
