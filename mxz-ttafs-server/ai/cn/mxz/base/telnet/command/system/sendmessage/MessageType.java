package cn.mxz.base.telnet.command.system.sendmessage;

public class MessageType {

	/**
	 * 1 (屏幕中部文字提示)该消息会叠加在之前的消息上
	 */
	public static final int	APPEND	= 1;

	/**
	 * 2 (屏幕中部文字提示)该消息会覆盖之前的消息
	 */
	public static final int	RECOVER	= 2;

	/**
	 * 3 (顶部滚屏公告)公告
	 */
	public static final int	NOTICE	= 3;
}
