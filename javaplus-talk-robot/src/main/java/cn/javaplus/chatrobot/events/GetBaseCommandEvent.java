package cn.javaplus.chatrobot.events;

import cn.javaplus.chatrobot.Answer;
import cn.javaplus.chatrobot.ChatContent;
import cn.javaplus.chatrobot.ChatRobot;

public class GetBaseCommandEvent {

	private ChatContent heSay;
	private ChatRobot robot;
	private Answer answer;

	public GetBaseCommandEvent(ChatContent last, ChatRobot robot, Answer answer) {
		this.heSay = last;
		this.robot = robot;
		this.answer = answer;
	}
	
	public ChatContent getHeSay() {
		return heSay;
	}
	
	public ChatRobot getRobot() {
		return robot;
	}
	
	public Answer getAnswer() {
		return answer;
	}

}
