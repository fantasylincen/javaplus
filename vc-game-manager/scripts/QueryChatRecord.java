import java.util.ArrayList;
import java.util.List;

import notice.ChatContent;
import notice.NoticeManager;
import user.UserInfo;
import util.GMUtil;
import util.UtilBase;
import cn.javaplus.string.StringPrinter;

import com.google.common.collect.Lists;

public class QueryChatRecord {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public static class ChatRecord {

		String time;
		String a;
		String b;
		String content;

		public ChatRecord(String a, String b, String t, String content) {
			this.a = a;
			this.b = b;
			this.time = t;
			this.content = content;
		}

		/**
		 * 聊天时间 格式 ： yyyy-MM-dd HH:mm:ss
		 * 
		 * @return
		 */
		public String getTime() {
			return time;
		}

		/**
		 * 发送者名字
		 * 
		 * @return
		 */
		public String getA() {
			return a;
		}

		/**
		 * 接受者名字
		 * 
		 * @return
		 */
		public String getB() {
			return b;
		}

		/**
		 * 聊天内容
		 * 
		 * @return
		 */
		public String getContent() {
			return content;
		}

	}

	/**
	 * 设置玩家某项属性
	 */
	public String query(String user) {
		UserInfo u = GMUtil.getUser(user);
		List<ChatRecord> records = getChatRecord(u);

		StringPrinter out = new StringPrinter();
		if (records.isEmpty())
			return "无";
		for (ChatRecord c : records) {
			out.print(c.getTime());
			out.print("<br>");
			out.print(c.getA() + " ---> " + c.getB() + " : " + c.getContent());
			out.print("<br>");
		}
		return out.toString();
	}

	public List<ChatRecord> getChatRecord(UserInfo u) {
		ArrayList<ChatRecord> ls = Lists.newArrayList();
		for (ChatContent chat : NoticeManager.getInstance().getChatContent()) {
			if (chat.getId() != -2)
				continue;
			if (Integer.parseInt(chat.getArgs()[0]) != u.getUID())
				continue;

			String a = u.getNickName();
			String t = UtilBase.secondsToDateStr(chat.getTime());
			String content = chat.getArgs()[4];
			ls.add(new ChatRecord(a, "世界", t, content));
		}
		return ls;
	}

}