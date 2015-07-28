package cn.javaplus.crazy.user;

public class UserExitEvent {

	private User user;

	public UserExitEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
