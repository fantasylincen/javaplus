package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpBattle;

/**
 * PVP 战斗胜利
 * @author 林岑
 *
 */
public class PvpFightingWinEvent {

	private PvpBattle battle;

	public PvpFightingWinEvent(PvpBattle battle) {
		this.battle = battle;
	}

	public PvpBattle getBattle() {
		return battle;
	}
}
