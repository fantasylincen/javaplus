package cn.javaplus.mxzrobot.listeners;

import game.log.Logs;
import cn.javaplus.chatrobot.ChatContent;
import cn.javaplus.chatrobot.events.ThinkingEvent;
import cn.javaplus.events.Listener;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.events.Ask;

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
			Logs.info("GM:" + heSay);
			String result = ac.execute(a.getArgs());
			e.getAnswer().set(result);
		}
	}

	private String trim(String c) {
		c = c.trim();
		
//		Pattern p = Pattern.compile("\\$\\{[^\\{^\\}^$]+\\}");
//		
//		while(true) {
//			Matcher m = p.matcher(c);
//			if(!m.find())
//				break;
//			String find = m.group();
//			
//			String temp = find.replaceAll("\\$", "\\\\\\$");
//			temp = temp.replaceAll("\\{", "\\\\\\{");
//			temp = temp.replaceAll("\\}", "\\\\\\}");
//			c = c.replaceFirst(temp, "${" + DES.encrypt(pickOut(find)) + "}");
//		}
		
		c = c.replaceAll(" ", "");//去掉所有空格
		return c;
	}
	
//	public static void main(String[] args) {
//		Pattern p = Pattern.compile("\\$\\{[^\\{^\\}^$]+\\}");
//		String a = "${fuck  fuck fuck } shit ${lll, lll}";
//		
//		while(true) {
//			Matcher m = p.matcher(a);
//			if(!m.find())
//				break;
//			String find = m.group();
//			
//			String temp = find.replaceAll("\\$", "\\\\\\$");
//			temp = temp.replaceAll("\\{", "\\\\\\{");
//			temp = temp.replaceAll("\\}", "\\\\\\}");
//			a = a.replaceFirst(temp, DES.encrypt(pickOut(find)));
//			
//			
//			System.out.println(a);
//		}
////		System.out.println(a.replaceFirst("\\$\\{.+\\}", "----"));
//	}
//
//	private static String pickOut(String find) {
//		String temp = find.replaceAll("\\$", "");
//		temp = temp.replaceAll("\\{", "");
//		temp = temp.replaceAll("\\}", "");
//		return temp;
//	}



}
