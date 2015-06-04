package cn.mxz.friend;

class RequestEvent {

	private String	userId;

	RequestEvent(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
}
