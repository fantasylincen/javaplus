package cn.javaplus.chatrobot;

import java.util.List;

import com.google.common.io.Resources;

import cn.javaplus.util.Util;

public class ChatRobot {

	private Mouth mouth;
	private Head head;
	private String name;
	private ChatRecorder recorder;

	public ChatRobot() {
		head = new Head(this);
		name = getRandomNick();
	}

	private String getRandomNick() {
		List<String> lines = Util.File.getLines(Resources.getResource("nicks"));
		return Util.Collection.getRandomOne(lines);
	}

	public void say(String message) {
		mouth.say(message);
		ChatContent t = new ChatContent(message, true);
		head.addTalkConent(t);
	}

	public void setMouth(Mouth mouth) {
		this.mouth = mouth;
	}

	public void hear(String heard) {
		ChatContent t = new ChatContent(heard, false);
		head.addTalkConent(t);
		new Thread(head).start();
	}

	/**
	 * 是否想退出
	 * 
	 * @return
	 */
	public boolean wantToExit() {
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public ChatRecorder getChatRecorder() {
		return recorder;
	}

	public void setRecorder(ChatRecorder recorder) {
		this.recorder = recorder;
	}
}
