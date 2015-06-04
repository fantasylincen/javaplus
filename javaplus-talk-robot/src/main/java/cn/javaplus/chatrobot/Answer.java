package cn.javaplus.chatrobot;

public class Answer {

	private String value;

	public boolean has() {
		return value != null;
	}

	public void set(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
