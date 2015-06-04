package cn.javaplus.chatrobot;

import java.util.List;

import com.google.common.collect.Lists;

public class ChatContents {

	private List<ChatContent> talkConents;
	private ChatContent lastMyTalk;
	private ChatContent lastHisTalk;

	public ChatContents() {
		talkConents = Lists.newArrayList();
	}

	public int size() {
		return talkConents.size();
	}

	public void add(ChatContent c) {
		if(c.isMyTalk()) {
			lastMyTalk = c;
		} else {
			lastHisTalk = c;
		}
		talkConents.add(c);
	}
	
	public ChatContent getLastHisTalk() {
		return lastHisTalk;
	}
	
	public ChatContent getLastMyTalk() {
		return lastMyTalk;
	}
	
	public List<ChatContent> getTalkConents() {
		return talkConents;
	}
}
