package cn.javaplus.chatrobot;

import cn.javaplus.chatrobot.define.D;
import cn.javaplus.chatrobot.events.BeforeThinkingEvent;
import cn.javaplus.chatrobot.events.GetAnswerEvent;
import cn.javaplus.chatrobot.events.GetBaseCommandEvent;
import cn.javaplus.chatrobot.events.ThinkOverEvent;
import cn.javaplus.chatrobot.events.ThinkingEvent;
import cn.mxz.events.Events;

public class Head implements Runnable {

	private static final Events ET = Events.getInstance();
	private final ChatRobot robot;
	private ChatContents talkConents;
	private Answer answer;
	private ChatContent last;

	Head(ChatRobot talkRobot) {
		robot = talkRobot;
		talkConents = new ChatContents();
	}

	public void run() {
		answer = new Answer();
		ET.dispatch(new BeforeThinkingEvent(robot, talkConents));
		last = talkConents.getLastHisTalk();
		thinking();
		ET.dispatch(new ThinkOverEvent(robot, answer));
	}

	private void thinking() {
		if (isBaseCommand()) {
			try {
				ET.dispatch(new GetBaseCommandEvent(last, robot, answer));
			} catch (Exception e) {
				answer.set(D.get("RESOLEVE_ERROR"));
			}
		} else {
			ET.dispatch(new ThinkingEvent(robot, answer, talkConents));
		}
		if (answer.has()) {
			ET.dispatch(new GetAnswerEvent(robot, answer));
		}
	}

	private boolean isBaseCommand() {
		ChatContent lastHisTalk = talkConents.getLastHisTalk();
		String s = lastHisTalk.toString();
		return s.startsWith(D.COMMAND_HEAD);
	}

	public void addTalkConent(ChatContent c) {
		this.talkConents.add(c);

		ChatRecorder cc = robot.getChatRecorder();
		if (cc != null) {
			cc.save(c.toString(), robot.getName());
		}
	}

}
