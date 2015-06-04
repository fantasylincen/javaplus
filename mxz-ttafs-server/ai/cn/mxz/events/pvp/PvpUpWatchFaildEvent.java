package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP 晋级赛晋级失败
 * @author 林岑
 *
 */
public class PvpUpWatchFaildEvent {

	private PvpPlayer player;

	public PvpUpWatchFaildEvent(PvpPlayer player) {
		this.player = player;
	}

	public PvpPlayer getPlayer() {
		return player;
	}
}
