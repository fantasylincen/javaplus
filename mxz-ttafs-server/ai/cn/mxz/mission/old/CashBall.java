package cn.mxz.mission.old;

import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

/**
 * 金币球
 * @author 林岑
 *
 */
public class CashBall {

	private int count;
	private Player player;

	public CashBall(Player player, int count) {
		this.player = player;
		this.count = count;
	}

	public void award() {

		player.add(PlayerProperty.CASH, getCount());		
	}

	public int getCount() {
		return count * player.getLevel();
	}

}
