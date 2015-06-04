//[消息提示]游戏公告package cn.mxz;public class GameNoticeTemplet {	/** 	 * ID 	 */	private int id;	/** 	 * 公告标题 	 */	private String name;	/** 	 * 类型
（1重要，2新活动，3新提示） 	 */	private int type;	/** 	 * 显示时间
 	 */	private String time;	/** 	 * 公告内容 	 */	private String content;	/**	 * ID	 */	void setId(int id) {		this.id = id;	}	/**	 * ID	 */	public int getId() {		return this.id;	}	/**	 * 公告标题	 */	void setName(String name) {		this.name = name;	}	/**	 * 公告标题	 */	public String getName() {		return this.name;	}	/**	 * 类型
（1重要，2新活动，3新提示）	 */	void setType(int type) {		this.type = type;	}	/**	 * 类型
（1重要，2新活动，3新提示）	 */	public int getType() {		return this.type;	}	/**	 * 显示时间
	 */	void setTime(String time) {		this.time = time;	}	/**	 * 显示时间
	 */	public String getTime() {		return this.time;	}	/**	 * 公告内容	 */	void setContent(String content) {		this.content = content;	}	/**	 * 公告内容	 */	public String getContent() {		return this.content;	}}