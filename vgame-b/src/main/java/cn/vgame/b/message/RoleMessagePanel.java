package cn.vgame.b.message;

import java.util.List;

import cn.vgame.b.account.Role;

public class RoleMessagePanel {

	private final Messages messages;
	private final Role role;

	public RoleMessagePanel(Messages messages, Role role) {
		this.messages = messages;
		this.role = role;
	}

	public List<Message> getMessages() {
		return messages.getMessages();
	}

	public String toJsonString() {
		return messages.toJsonString();
	}

	public long getLaBa() {
		return role.getLaBa();
	}

	public long getCoin() {
		return role.getCoin();
	}


}
