package notice;

import lombok.Data;
import deng.xxoo.utils.XOTime;

public class ChatContent {

	private int id;
	private String[] args;
	private int time;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ChatContent(int id, String[] args) {
		this.id 	= id;
		this.args 	= args;
		this.time 	= XOTime.currentTimeSecond();
	}

}
