package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpBattle;

/**
 * pvp 普通战斗胜利
 * @author 林岑
 *
 */
public class PvpNormalWatchWinEvent {

	private PvpBattle battle;

	public PvpNormalWatchWinEvent(PvpBattle battle) {
		this.battle = battle;
	}

	public PvpBattle getBattle() {
		return battle;
	}
}
