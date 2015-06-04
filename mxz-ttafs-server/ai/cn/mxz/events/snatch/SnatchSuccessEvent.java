package cn.mxz.events.snatch;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;

/**
 * 夺宝成功
 * 
 * @author 林岑
 */
public class SnatchSuccessEvent {

	private City city;
	private Battle battle;
	private boolean isRobot;
	private String userId;
	private int stuffTempletId;
	private int warsituationId;

	public SnatchSuccessEvent(City city, Battle battle, boolean isRobot,
			String userId, int stuffTempletId, int warsituationId) {
		this.city = city;
		this.battle = battle;
		this.isRobot = isRobot;
		this.userId = userId;
		this.stuffTempletId = stuffTempletId;
		this.warsituationId = warsituationId;
	}

	public int getWarsituationId() {
		return warsituationId;
	}

	public City getCity() {
		return city;
	}

	public Battle getBattle() {
		return battle;
	}

	public boolean isRobot() {
		return isRobot;
	}

	public String getUserId() {
		return userId;
	}

	public int getStuffTempletId() {
		return stuffTempletId;
	}

}
