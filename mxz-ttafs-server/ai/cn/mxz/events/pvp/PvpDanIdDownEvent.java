package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP段位等级下降
 * @author 林岑
 *
 */
public class PvpDanIdDownEvent {

	private PvpPlayer player;

	public PvpDanIdDownEvent(PvpPlayer player) {
		this.player = player;
	}

	public PvpPlayer getPlayer() {
		return player;
	}
}
