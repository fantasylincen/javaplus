package cn.mxz.events.snatch;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;

/**
 * 夺宝结束
 * 
 * @author 林岑
 */
public class SnatchOverEvent {

	private City robber;
	private Battle battle;
	private boolean isRobot;
	private String userId;
	private int stuffTempletId;
	private int warsituationId;
	private boolean isSuccess;

	public SnatchOverEvent(City robber, Battle battle, boolean isRobot,
			String userId, int stuffTempletId, int warsituationId, boolean isSuccess) {
		this.robber = robber;
		this.battle = battle;
		this.isRobot = isRobot;
		this.userId = userId;
		this.stuffTempletId = stuffTempletId;
		this.warsituationId = warsituationId;
		this.isSuccess = isSuccess;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}



	public int getWarsituationId() {
		return warsituationId;
	}

	public City getRobber() {
		return robber;
	}

	public Battle getBattle() {
		return battle;
	}

	public boolean isRobot() {
		return isRobot;
	}

	/**
	 * 被夺宝的玩家
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public int getStuffTempletId() {
		return stuffTempletId;
	}
}
