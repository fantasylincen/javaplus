package cn.mxz.chat;

import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class KeFuChatUIAdaptor implements ChatUI {

	public KeFuChatUIAdaptor(City user) {
	}

	@Override
	public List<ChatMessageUI> getMessages() {
		return Lists.newArrayList();
	}

}
