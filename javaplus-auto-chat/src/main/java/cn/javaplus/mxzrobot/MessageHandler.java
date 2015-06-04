package cn.javaplus.mxzrobot;

import iqq.im.QQClient;
import iqq.im.bean.QQGroupMember;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQUser;
import iqq.im.bean.content.TextItem;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.time.taskutil.TaskCenter;
import cn.javaplus.time.taskutil.TaskSafety;
import cn.javaplus.util.Util;

public class MessageHandler extends Thread {

	public class MyTask extends TaskSafety {

		private String content;
		private String to;

		public MyTask(String content, String to) {
			this.content = content;
			this.to = to;
		}

		@Override
		protected void process(Exception e) {
			e.printStackTrace();
		}

		@Override
		public void runSafty() {
			sendMessage();
		}

		private void sendMessage() {
			 QQMsg sendMsg = new QQMsg();
			 QQUser tt = new QQUser() {
			};
//			QQGroupMember m = new QQGroupMember();
//			m.set
//			sendMsg.setTo(tt);
//			 sendMsg.setType(QQMsg.Type.BUDDY_MSG);
//			 sendMsg.addContentItem(new TextItem(content));
//			 client.sendMsg(sendMsg, null);			
		}

	}

	private QQClient client;

	private long lastModified;

	private TaskCenter timer;

	public MessageHandler(QQClient client) {
		this.client = client;
		timer = new TaskCenter();
	}

	@Override
	public void run() {
		while (true) {
			if (fileChanged()) {
				timer.cancel();
				timer = new TaskCenter();
				reloadAllMessage();
			}
			Util.Thread.sleep(5000);
		}
	}

	private void reloadAllMessage() {
		SAXReader r = new SAXReader();
		try {
			File file = new File("messages.xml");
			Document read = r.read(file);
			resolve(read.getRootElement());

			lastModified = file.lastModified();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析
	 * 
	 * @param root
	 */
	@SuppressWarnings({ "unchecked" })
	private void resolve(Element root) {
		List<Element> all = root.elements("message");
		for (Element m : all) {
			String time = m.elementTextTrim("time");
			String content = m.elementText("content");
			String to = m.elementText("to");
			addTask(time, content, to);
		}
	}

	private void addTask(String time, String content, String to) {

		// 19:30  (每天 19:30 )
		String r2 = "[0-9]{2}:[0-9]{2}";

		if (!time.matches(r2)) {
			throw new RuntimeException("时间格式不合法");
		}

		timer.loopEveryDay(time, new MyTask(content, to));
		
	}

	private boolean fileChanged() {
		File f = new File("messages");
		long lastModified = f.lastModified();
		return this.lastModified != lastModified;
	}


}
