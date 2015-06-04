package com.linekong.platform.protocol.erating.server;

import cn.mxz.city.City;

public class RechargeEvent {

	private int count;
	private City user;

	public RechargeEvent(City user, int count) {
		this.user = user;
		this.count = count;
	}

	/**
	 * 充值数量
	 * 
	 * @return
	 */
	public int getRechargeCount() {
		return count;
	}

	public City getUser() {
		return user;
	}
}
