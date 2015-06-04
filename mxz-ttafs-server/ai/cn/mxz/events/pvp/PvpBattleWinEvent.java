package cn.mxz.events.pvp;

import cn.mxz.battle.Battle;

public class PvpBattleWinEvent {

	private int	situationId;
	private Battle	battle;

	public PvpBattleWinEvent(Battle battle, int situationId) {
		this.battle = battle;
		this.situationId = situationId;
	}

	public Battle getBattle() {
		return battle;
	}

	public int getSituationId() {
		return situationId;
	}
}
