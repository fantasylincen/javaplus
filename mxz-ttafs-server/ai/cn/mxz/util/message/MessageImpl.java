package cn.mxz.util.message;

import cn.javaplus.comunication.IPacket;
import cn.javaplus.comunication.PacketCompressedByZlib;
import cn.javaplus.message.Message;
import cn.mxz.MessageTempletConfig;

import com.lemon.commons.socket.ISocket;

abstract class MessageImpl implements Message {

	/**
	 * 消息码
	 */
	private int code;

	/**
	 * 消息对应的格式化字符串
	 */
	private Object[] info = new Object[0];

	
	public MessageImpl(int code, Object... info) {
		this.code = code;

		if(info != null && info.length != 0) {
			this.info = info;
		}
	}

	public MessageImpl() {
	}

	@Override
	public void setInfo(Object... info) {
		this.info = info;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public int getCode () {
		return code;
	}

	@Override
	public Object[] getFormatWords() {
		return info;
	}

	@Override
	public String toString() {

		String m = getMessage(code);

		for (int i = 0; i < info.length; i++) {
			m = m.replaceAll("%s" + i, info[i].toString());
		}

		return m;
	}

	@Override
	public final void send(ISocket socket) {
		final IPacket p = new PacketCompressedByZlib();

		p.putInt(getPacketId());

		p.putInt(getCode());
		p.putInt(info.length);
		for (Object o : info) {
			p.putString(o.toString());
		}

		p.send(socket);
	}

	protected abstract int getPacketId();

	private final String getMessage(int code) {
		return MessageTempletConfig.get(code).getContent();
	}

}