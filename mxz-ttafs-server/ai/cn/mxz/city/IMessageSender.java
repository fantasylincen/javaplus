package cn.mxz.city;

public interface IMessageSender {

	/**
	 * @param code
	 *            消息号
	 */
	void send(int code, Object... infos);

	/**
	 * 发送文字, 在开发阶段, 建议使用该方法发送,
	 * 发布时再统一把所有调用该方法的地方给替换成 send(int code, Object... infos) 方法
	 * @param text
	 */
	void sendText(String text);

	/**
	 * 构造文字信息
	 * @param code
	 * @param infos
	 * @return
	 */
	String buildText(int code, Object... infos);

	/**
	 * 暂时关闭消息发送器 , 关闭之后, send方法不起作用, 调用open后, 起作用
	 */
	void close();

	/**
	 * 打开消息发送器
	 */
	void open();

}
