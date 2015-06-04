package cn.mxz.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageTimeFormat extends SimpleDateFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395841968677067717L;

	public MessageTimeFormat() {
		super("MM-dd HH:mm");
	}

	public String format(long time) {
		Date d = new Date(time);
		return format(d);
	}
}
