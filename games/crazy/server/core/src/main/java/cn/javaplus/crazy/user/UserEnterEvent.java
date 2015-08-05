package cn.javaplus.crazy.user;

public class UserEnterEvent {

	private User user;
	private int clientId;

	public UserEnterEvent(User user, int clientId) {
		this.user = user;
		this.clientId = clientId;
	}

	public User getUser() {
		return user;
	}

	public int getClientId() {
		return clientId;
	}
}
