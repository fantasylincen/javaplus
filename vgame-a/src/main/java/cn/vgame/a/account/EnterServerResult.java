package cn.vgame.a.account;

public class EnterServerResult {

	private final String id;
	private final String sessionId;

	public EnterServerResult(String id, String sessionId) {
		this.id = id;
		this.sessionId = sessionId;
	}

	public String getId() {
		return id;
	}

	public String getSessionId() {
		return sessionId;
	}
}
