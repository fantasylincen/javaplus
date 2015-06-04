package cn.javaplus.chatrobot.events;

import cn.javaplus.chatrobot.Answer;
import cn.javaplus.chatrobot.ChatContents;
import cn.javaplus.chatrobot.ChatRobot;

public class ThinkingEvent {

	private ChatRobot robot;
	private Answer answer;
	private ChatContents talkConents;

	public ThinkingEvent(ChatRobot robot, Answer answer,
			ChatContents talkConents) {
		this.robot = robot;
		this.answer = answer;
		this.talkConents = talkConents;
	}

	public Answer getAnswer() {
		return answer;
	}

	public ChatRobot getRobot() {
		return robot;
	}

	public ChatContents getTalkConents() {
		return talkConents;
	}

}
