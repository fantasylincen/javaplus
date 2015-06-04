package cn.mxz.events;

import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;

/**
 * 副本胜利事件
 * @author 林岑
 */
public class MissionFightingWinEvent {

	private MissionBattle battle;
	private City user;
	private int mapId;
	private boolean isNewPlayer;

	public MissionFightingWinEvent(MissionBattle battle, City user, int mapId,
			boolean isNewPlayer) {
				this.battle = battle;
				this.user = user;
				this.mapId = mapId;
				this.isNewPlayer = isNewPlayer;
	}

	public MissionBattle getBattle() {
		return battle;
	}

	public City getUser() {
		return user;
	}

	public int getMapId() {
		return mapId;
	}

	/**
	 * 是否是新手玩家
	 * @return
	 */
	public boolean isNewPlayer() {
		return isNewPlayer;
	}
	

}
