package cn.mxz.mission.old.events;

import cn.mxz.city.City;
import cn.mxz.mission.IMissionManager;

/**
 * 玩家进入地图事件
 * 
 * @author 林岑
 * 
 */
public class EnterEvent {

	private City	source;

	public EnterEvent(City city) {
		this.source = city;
	}

	public City getSouce() {
		return source;
	}

	public int getMaxMissionId() {
		IMissionManager mission = source.getMission();
		return mission.getMaxMissionId();
	}
}
