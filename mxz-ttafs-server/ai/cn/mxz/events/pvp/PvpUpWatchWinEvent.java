package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpPlayer;

/**
 * 晋级赛单场战斗胜利
 * @author 林岑
 */
public class PvpUpWatchWinEvent {

	private PvpPlayer player;

	public PvpUpWatchWinEvent(PvpPlayer player) {
		this.player = player;
	}
	
	public PvpPlayer getPlayer() {
		return player;
	}

}
