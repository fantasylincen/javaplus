package cn.javaplus.message;

import cn.javaplus.commons.socket.ISocket;

/**
 * 消息
 * @author 	林岑
 * @time	2012年10月2日 09:47:34
 */
public interface Message {

	/**
	 * 格式化字符串
	 * @param info
	 */
	public abstract void setInfo(Object... info);

	/**
	 * 消息号
	 * @param code
	 */
	public abstract void setCode(int code);

	/**
	 * 消息号
	 * @return
	 */
	public abstract int getCode();

	/**
	 * 格式化字符串
	 */
	public abstract Object[] getFormatWords();

	/**
	 * 发送该消息
	 */
	public abstract void send(ISocket socket);

}