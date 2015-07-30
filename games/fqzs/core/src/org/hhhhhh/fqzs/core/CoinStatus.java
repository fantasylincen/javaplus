package org.hhhhhh.fqzs.core;

public class CoinStatus {

	/**
	 * 可以领取的金币
	 */
	 long coin;

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public boolean isCanReceive() {
		return isCanReceive;
	}

	public void setCanReceive(boolean isCanReceive) {
		this.isCanReceive = isCanReceive;
	}

	/**
	 * 下次可以领取时间
	 */
	 int cd;

	/**
	 * 是否可以领取
	 */
	 boolean isCanReceive;

}
