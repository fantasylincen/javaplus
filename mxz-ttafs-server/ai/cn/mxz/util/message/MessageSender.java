package cn.mxz.util.message;

import cn.javaplus.message.Message;
import cn.mxz.util.Factory;

import com.lemon.commons.socket.ISocket;

/**
 * 中部文字提示
 * @author 林岑
 *
 */
public class MessageSender {

	public static final void sendMessage(ISocket socket, int code, Object... info) {

		final Message m = (Message) Factory.get("textMessage");

		m.setCode(code);

		m.setInfo(info);

		m.send(socket);
	}
}
