package cn.javaplus.comunication;

public class OnDataEvent<U extends ProtocolUser> {

	private U	user;
	private Request	request;

	public OnDataEvent(U user, Request request) {
		this.user = user;
		this.request = request;
	}

	public U getUser() {
		return user;
	}
	public Request getRequest() {
		return request;
	}
}
