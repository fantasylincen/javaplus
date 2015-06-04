package cn.javaplus.mxzrobot.listeners;

import cn.javaplus.chatrobot.ChatContent;
import cn.javaplus.chatrobot.events.ThinkingEvent;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.events.Ask;
import cn.mxz.events.Listener;

public class AnswerByProperties implements Listener<ThinkingEvent> {

	public void onEvent(ThinkingEvent e) {
		ChatContent heSay = e.getTalkConents().getLastHisTalk();
		String c = heSay.getContent();

		c = trim(c);
		
		Ask a = new AskFinder().find(c);
		if (a == null) {
			e.getAnswer().set("i can't understand");
		} else {
			Action ac = a.getAction();
			String result = ac.execute(a.getArgs());
			e.getAnswer().set(result);
		}
	}

	private String trim(String c) {
		c = c.trim();
		c = c.replaceAll(" ", "");//去掉所有空格
		return c;
	}



}
