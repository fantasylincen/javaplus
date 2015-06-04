package cn.javaplus.server;

import java.net.Socket;

import cn.javaplus.comunication.ProtocolUser;

import com.lemon.commons.socket.ISocket;

public class TestUser implements ProtocolUser {

	private String	id;
	private ISocket	socket;

	public TestUser(String id, Socket socket) {
		this.id = id;
		this.socket = new ISocketImpl(socket);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public ISocket getSocket() {
		return socket;
	}

}
