package cn.mxz.events.newpvp;

import java.util.List;

import cn.mxz.newpvp.PvpPlayer;

public class PvpPlaceResortEvent {

	private List<PvpPlayer> players;

	public PvpPlaceResortEvent(List<PvpPlayer> players) {
		this.players = players;
	}

	public List<PvpPlayer> getPlayers() {
		return players;
	}

}
