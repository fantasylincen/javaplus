package cn.mxz.chat;

public interface ChatMessage {
	
	int getTypeId();

	int getStep();

	String getNick();

	int getLevel();

	int getVipLevel();

	String getContent();

	/**
	 * 发送者的ID
	 * @return
	 */
	String getId();

	long getTime();

	String getFormatTime();
}
