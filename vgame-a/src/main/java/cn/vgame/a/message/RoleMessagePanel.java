package cn.vgame.a.message;

import java.util.List;

import cn.vgame.a.account.Role;
import cn.vgame.a.receivecoin.CoinStatus;

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

	public long getJiangQuan() {
		return role.getJiangQuan();
	}

	public long getCd() {
		CoinStatus ss = role.getCoinStatus();
		return ss.getCd();
	}

}
