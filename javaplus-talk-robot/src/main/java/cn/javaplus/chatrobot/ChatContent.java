package cn.javaplus.chatrobot;

public class ChatContent {

	private boolean isMyTalk;
	private String content;

	public ChatContent(String content, boolean isMyTalk) {
		this.content = content;
		this.isMyTalk = isMyTalk;
	}

	public boolean isMyTalk() {
		return isMyTalk;
	}

	public String getContent() {
		return content;
	}
	
	
	@Override
	public String toString() {
		return content;
	}
}
