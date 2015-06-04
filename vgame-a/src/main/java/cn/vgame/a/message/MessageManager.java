package cn.vgame.a.message;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.SystemKeyValueDao;
import cn.vgame.a.gen.dto.MongoGen.SystemKeyValueDto;
import cn.vgame.share.Xml;

public class MessageManager {

	static final String key = "USER_MESSAGES";
	private Messages messages;

	public MessageManager() {
		SystemKeyValueDao keyValue = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = keyValue.get(key);
		if(dto == null) {
			dto = new SystemKeyValueDto();
			dto.setKey(key);
			dto.setValue("[]");
			keyValue.save(dto);
		}
		String userMessages = dto.getValue();
		messages = new Messages(userMessages);
	}

	/**
	 * 给所有玩家发送消息(小喇叭)
	 * @param role 
	 * 
	 * @param message
	 */
	public void sendMessage(String nick, String message) {
		Message m = new Message(nick, message);
		
		messages.addMessage(m);
		
		SystemKeyValueDao keyValue = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = keyValue.get(key);
		dto.setValue(messages.toJsonString());
		keyValue.save(dto);
	}

	public Messages getMessages() {
		return messages;
	}

	/**
	 * 发送滚屏公告
	 * @param code 消息ID
	 * @param messages 消息内容列表
	 */
	public void sendNotice(int code, Object... messages) {
		String mess = buildMessage(code, messages);
		String head = buildMessage(10108);
		sendMessage(head, mess);
	}

	public String buildMessage(int code, Object... messages) {
		Sheet mess = Xml.getSheet("messages");
		Row row = mess.get(code + "");
		String messageBase = row.get("value");
		for (int i = 0; i < messages.length; i++) {
			String m = messages[i].toString();
			String regex = "%s" + i;
			messageBase = messageBase.replaceAll(regex, m);
		}
		return messageBase;
	}

}
