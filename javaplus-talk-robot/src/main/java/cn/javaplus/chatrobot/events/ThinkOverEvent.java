package cn.javaplus.chatrobot.events;

import cn.javaplus.chatrobot.Answer;
import cn.javaplus.chatrobot.ChatRobot;

public class ThinkOverEvent {

	private ChatRobot robot;
	private Answer answer;

	public ThinkOverEvent(ChatRobot robot, Answer answer) {
		this.robot = robot;
		this.answer = answer;
	}
	
	public ChatRobot getRobot() {
		return robot;
	}

	public Answer getAnswer() {
		return answer;
	}
}
