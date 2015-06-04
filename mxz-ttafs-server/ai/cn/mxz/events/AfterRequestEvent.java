package cn.mxz.events;

import java.util.List;

import cn.javaplus.comunication.Response;
import cn.mxz.city.City;

import com.lemon.commons.socket.ISocket;

public class AfterRequestEvent {

	private City			user;
	private int				packetId;
	private List<Response>	responses;
	private ISocket socket;

	public AfterRequestEvent(ISocket socket , City user, int packetId, List<Response> responses) {
		this.socket = socket;
		this.user = user;
		this.packetId = packetId;
		this.responses = responses;
	}

	public City getUser() {
		return user;
	}

	public int getPacketId() {
		return packetId;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public ISocket getSocket() {
		return socket;
	}
}
