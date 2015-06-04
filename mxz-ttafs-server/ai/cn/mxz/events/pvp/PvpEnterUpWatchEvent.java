package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpPlayer;

/**
 * pvp进入晋级赛
 * @author 林岑
 *
 */
public class PvpEnterUpWatchEvent {

	private PvpPlayer player;

	public PvpEnterUpWatchEvent(PvpPlayer player) {
		this.player = player;
	}

	public PvpPlayer getPlayer() {
		return player;
	}
}
