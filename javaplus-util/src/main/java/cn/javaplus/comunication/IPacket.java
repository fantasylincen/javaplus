package cn.javaplus.comunication;

import cn.javaplus.commons.socket.ISocket;

/**
 * 数据包
 * @author 	林岑
 * @time	2013-5-28
 */
public interface IPacket {

	/**
	 * 放入一个协议
	 */
	void put(PacketStruct p);

	/**
	 * 发送该数据包
	 */
	void send(ISocket u);

	void putInt(int value);
	void putDouble(double value);
	void putString(String value);
	void put(Bytes message);
	void putBoolean(boolean value);
}