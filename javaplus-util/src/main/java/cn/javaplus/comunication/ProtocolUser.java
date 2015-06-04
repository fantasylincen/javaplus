package cn.javaplus.comunication;

import cn.javaplus.commons.socket.ISocket;

public interface ProtocolUser {

	String getId();

	ISocket getSocket();
}
