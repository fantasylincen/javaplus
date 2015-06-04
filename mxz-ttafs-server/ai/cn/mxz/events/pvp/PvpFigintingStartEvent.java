package cn.mxz.events.pvp;

import cn.mxz.newpvp.PvpBattle;

/**
 * PVP 开战
 * @author 林岑
 *
 */
public class PvpFigintingStartEvent {

	private PvpBattle pvpBattle;

	public PvpFigintingStartEvent(PvpBattle pvpBattle) {
		this.pvpBattle = pvpBattle;
	}
	
	public PvpBattle getBattle() {
		return pvpBattle;
	}

}
