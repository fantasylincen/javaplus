package cn.javaplus.chatrobot.listeners;

import cn.javaplus.chatrobot.ChatRobot;
import cn.javaplus.chatrobot.events.GetAnswerEvent;
import cn.mxz.events.Listener;

public class SayAnswer implements Listener<GetAnswerEvent> {

	public void onEvent(GetAnswerEvent e) {
		String value = e.getAnswer().getValue();
		ChatRobot robot = e.getRobot();
		robot.say(value);
	}

}
