package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP 晋级成功
 * 
 * @author 林岑
 * 
 */
public class PvpUpSuccessEvent {

	private PvpPlayer player;

	public PvpUpSuccessEvent(PvpPlayer player) {
		this.player = player;
	}

	public PvpPlayer getPlayer() {
		return player;
	}

}
