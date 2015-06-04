package notice;

import lombok.Data;
import deng.xxoo.utils.XOTime;

@Data
public class ChatContent {

	private int id;
	private String[] args;
	private int time;
	
	public ChatContent(int id, String[] args) {
		this.id 	= id;
		this.args 	= args;
		this.time 	= XOTime.currentTimeSecond();
	}

}
