package cn.javaplus.chatrobot.events;

import cn.javaplus.chatrobot.ChatContents;
import cn.javaplus.chatrobot.ChatRobot;


public class BeforeThinkingEvent {

	private ChatRobot robot;
	private ChatContents talkConents;

	public BeforeThinkingEvent(ChatRobot robot, ChatContents talkConents) {
		this.robot = robot;
		this.talkConents = talkConents;
	}
	
	public ChatRobot getRobot() {
		return robot;
	}
	
	public ChatContents getTalkConents() {
		return talkConents;
	}

}
