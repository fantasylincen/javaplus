package cn.mxz.log;

public enum LogType {

	/**
	 * PVP 日志记录
	 */
	PVP,

	/**
	 * 留言
	 */
	LEAVE_MESSAGE,

	;

	/**
	 * 唯一标识符
	 * @return
	 */
	public int getId() {

		return ordinal();
	}

}
