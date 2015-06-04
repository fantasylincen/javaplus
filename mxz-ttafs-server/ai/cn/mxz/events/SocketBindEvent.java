package cn.mxz.events;

import cn.mxz.city.City;

import com.lemon.commons.socket.ISocket;

public class SocketBindEvent {

	private ISocket socket;
	private City user;

	public SocketBindEvent(City user, ISocket socket) {
		this.user = user;
		this.socket = socket;
	}

	public City getUser() {
		return user;
	}

	public ISocket getSocket() {
		return socket;
	}

}
