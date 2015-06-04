package com.cnbizmedia.recharge;

public class Order implements Comparable<Order> {

	public String getReason() {
		return dto.getReason();
	}

	IOrderDto dto;
	private final boolean isOk;

	public Order(IOrderDto dto, boolean isOk) {
		this.dto = dto;
		this.isOk = isOk;
	}

	public String getId() {
		return dto.getId();
	}

	public int getCount() {
		return dto.getCount();
	}

	public String getServerId() {
		return dto.getServerId();
	}

	public long getTime() {
		return dto.getTime();
	}

	public String getUserId() {
		return dto.getUserId();
	}

	public String getRechargeUserId() {
		return dto.getRechargeUserId();
	}

	public boolean getIsError() {
		return dto.getIsError();
	}

	public boolean isOk() {
		return isOk;
	}

	@Override
	public int compareTo(Order o) {
		int t = (int) (o.getTime() - getTime());
		return t;
	}
}
