package cn.mxz.friend;

class AcceptEvent {

	private String	userId;

	AcceptEvent(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
}
