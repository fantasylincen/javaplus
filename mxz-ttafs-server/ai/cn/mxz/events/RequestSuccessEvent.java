package cn.mxz.events;

import cn.javaplus.comunication.Request;
import cn.mxz.city.City;

import com.lemon.commons.socket.ISocket;

public class RequestSuccessEvent {

	private Request request;
	private long runTime;
	private City user;
	private ISocket socket;

	public RequestSuccessEvent(Request request, long runTime, City user, ISocket socket) {
		this.request = request;
		this.runTime = runTime;
		this.user = user;
		this.socket = socket;
	}
	
	public Request getRequest() {
		return request;
	}
	
	public long getRunTime() {
		return runTime;
	}
	
	public City getUser() {
		return user;
	}
	
	public ISocket getSocket() {
		return socket;
	}

}
