package cn.mxz.events;

import cn.mxz.battle.Battle;
import cn.mxz.formation.PlayerCamp;

public class FightingStartEvent {

	private PlayerCamp	under;
	private PlayerCamp	upper;
	private Battle battle;

	public FightingStartEvent(Battle battle, PlayerCamp under, PlayerCamp upper) {
		this.battle = battle;
		this.under = under;
		this.upper = upper;
	}

	public PlayerCamp getUnder() {
		return under;
	}

	public PlayerCamp getUpper() {
		return upper;
	}
	
	public Battle getBattle() {
		return battle;
	}
}
