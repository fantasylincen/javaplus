package cn.mxz.events;

import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;

public class MoppingUpEvent {

	private List<Prize> prize;
	private int count;
	private City user;
	private int missionId;
	private int type;

	public MoppingUpEvent(List<Prize> prize, int count, City user, int missionId, int type) {
		this.prize = prize;
		this.count = count;
		this.user = user;
		this.missionId = missionId;
		this.type = type;
	}

	public List<Prize> getPrize() {
		return prize;
	}
	
	public int getCount() {
		return count;
	}
	
	public City getUser() {
		return user;
	}
	
	public int getMissionId() {
		return missionId;
	}
	
	/**
	 * 	扫荡类型		1、主线   2、支线  3、小怪
	 */
	public int getType() {
		return type;
	}
}
