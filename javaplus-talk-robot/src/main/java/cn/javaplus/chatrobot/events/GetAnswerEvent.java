package cn.javaplus.chatrobot.events;

import cn.javaplus.chatrobot.Answer;
import cn.javaplus.chatrobot.ChatRobot;

public class GetAnswerEvent {

	private ChatRobot robot;
	private Answer answer;

	public GetAnswerEvent(ChatRobot robot, Answer answer) {
		this.robot = robot;
		this.answer = answer;
	}

	public Answer getAnswer() {
		return answer;
	}
	
	public ChatRobot getRobot() {
		return robot;
	}
}
