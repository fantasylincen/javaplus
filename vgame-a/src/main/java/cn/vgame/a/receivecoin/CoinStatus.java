package cn.vgame.a.receivecoin;

import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.share.KeyValue;

public class CoinStatus {

	private final Role role;

	public CoinStatus(Role role) {
		this.role = role;
	}

	/**
	 * 可以领取的金币
	 */
	public long getCoin() {
		int count = Server.getConst().getInt("COIN_RECEIVE");
		return count;
	}

	/**
	 * 下次可以领取时间
	 */
	public int getCd() {

		long miles = System.currentTimeMillis();

		KeyValue forever = role.getKeyValueForever();
		Long nextTime = forever.getLong("NEXT_RECEIVE_COIN_TIME");

		if (nextTime == null)
			return 0;

		long t = nextTime - miles;
		if (t < 0)
			t = 0;

		return (int) t / 1000;
	}

	/**
	 * 是否可以领取
	 */
	public boolean isCanReceive() {

		if (getCd() > 0)
			return false;
		int count = Server.getConst().getInt("COIN_RECEIVE_CONDITION");

		return role.getCoinAll() < count;
	}

}
