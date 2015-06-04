package cn.mxz.events.pvp;

import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;

public class PvpFightingStartEvent {

	private PvpBattle battle;
	private PlayerCamp under;
	private PlayerCamp upper;

	public PvpFightingStartEvent(PvpBattle battle, PlayerCamp under,
			PlayerCamp upper) {
		this.battle = battle;
		this.under = under;
		this.upper = upper;
	}

	public PvpBattle getBattle() {
		return battle;
	}

	public PlayerCamp getUnder() {
		return under;
	}

	public PlayerCamp getUpper() {
		return upper;
	}
}
