package cn.mxz.mission;

import cn.mxz.mission.star.MissionStarManager;

public class StarStatus {

	private int	type;
	private MissionStarManager	manager;
	private int	chapterId;

	public StarStatus(int chapterId, int type,  MissionStarManager manager) {
		this.chapterId = chapterId;
		this.type = type;
		this.manager = manager;
	}

	public boolean getHasReceive() {
		return manager.hasReceive(chapterId, type);
	}

	public boolean getCanReceive() {
		return manager.canReceive(chapterId, type);
	}

}
