package cn.mxz.events;

import java.util.List;

import cn.mxz.newpvp.PvpFightUser;
import cn.mxz.newpvp.PvpPlayer;

public class RandomPvpRobotEvent {

	private List<PvpFightUser> threePlayers;
	private PvpPlayer player;

	public RandomPvpRobotEvent(List<PvpFightUser> ls, PvpPlayer player) {
		this.threePlayers = ls;
		this.player = player;
	}
	
	public PvpPlayer getPlayer() {
		return player;
	}
	
	public List<PvpFightUser> getThreePlayers() {
		return threePlayers;
	}
}
