package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 用户请求前 的事件
 *
 * @author 林岑
 *
 */
public class BeforeRequestEvent {

	private City	user;
	private int	packetId;

	public BeforeRequestEvent(City user, int packetId) {
		this.user = user;
		this.packetId = packetId;
	}

	public City getUser() {
		return user;
	}

	public int getPacketId() {
		return packetId;
	}
}
