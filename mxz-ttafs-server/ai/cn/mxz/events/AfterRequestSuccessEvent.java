package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 客户端请求正常结束后的事件
 * @author 林岑
 *
 */
public class AfterRequestSuccessEvent {

	private City	user;
	private int	packetId;

	public AfterRequestSuccessEvent(City user, int packetId) {
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
