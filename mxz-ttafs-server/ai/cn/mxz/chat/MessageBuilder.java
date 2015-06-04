package cn.mxz.chat;

import cn.mxz.city.City;

public class MessageBuilder {

	// 格式: 发送者ID|昵称|等级|性别(0:女 1:男)|主角神将ID|消息内容|是否仇敌(1/0)|是否朋友(1/0)|03-10 13:12
	public String build(Message m, City receiver, String sender) {
		boolean isEnemy = receiver.getEnemyManager().isEnemy(sender);
		boolean isFriend = receiver.getFriendManager().isFriend(sender);

		StringBuilder sb = new StringBuilder(m.getMessage());
		sb.append("|" + (isEnemy ? 1 : 0 ));
		sb.append("|" + (isFriend ? 1 : 0 ));
		sb.append("|" + m.getFormatTime());
		return sb.toString();
	}
	
	public String getContent(String messageReal) {
		return messageReal.split("\\|")[5].trim();
	}
}
