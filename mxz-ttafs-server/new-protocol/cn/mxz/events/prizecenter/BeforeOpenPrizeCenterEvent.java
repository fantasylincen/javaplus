package cn.mxz.events.prizecenter;

import cn.mxz.city.City;

/**
 * 打开领奖中心前
 * @author 林岑
 *
 */
public class BeforeOpenPrizeCenterEvent {

	private City user;

	public BeforeOpenPrizeCenterEvent(City user) {
		this.user = user;
	}

	public City getUser() {
		return user;
	}
}
