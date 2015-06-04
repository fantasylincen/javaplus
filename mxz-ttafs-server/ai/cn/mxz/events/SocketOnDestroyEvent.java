package cn.mxz.events;

import cn.mxz.city.City;

import com.lemon.commons.socket.ISocket;

public class SocketOnDestroyEvent {

	private ISocket socket;
	private City user;

	public SocketOnDestroyEvent(ISocket socket, City user) {
		this.socket = socket;
		this.user = user;
	}

	public ISocket getSocket() {
		return socket;
	}

	public City getUser() {
		return user;
	}
}
