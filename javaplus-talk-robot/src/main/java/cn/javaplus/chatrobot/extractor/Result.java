package cn.javaplus.chatrobot.extractor;

public class Result {

	private boolean isArg;
	private String name;
	private String content;

	public Result(boolean isArg, String name, String content) {
		this.isArg = isArg;
		this.name = name;
		this.content = content;
	}

	public boolean isArg() {
		return isArg;
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Result [isArg=" + isArg + ", name=" + name + ", content="
				+ content + "]";
	}
}
