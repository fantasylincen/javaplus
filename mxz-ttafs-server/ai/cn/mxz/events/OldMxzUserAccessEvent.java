package cn.mxz.events;

import cn.mxz.city.City;

/**
 * 漫想族内部老账号接入
 * @author 林岑
 */
public class OldMxzUserAccessEvent {

	private City user;

	public OldMxzUserAccessEvent(City user) {
		this.user = user;
	}

	public City getUser() {
		return user;
	}

}
