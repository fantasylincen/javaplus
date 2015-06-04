package cn.mxz.mission.type;

import cn.mxz.city.City;

public class EmptyEvent implements IEvent {

	public EmptyEvent() {
	}
	@Override
	public Object run(City user) {

//		Debuger.debug("PrizeEvent.run() Empty 事件");
		return null;
	}

	@Override
	public int getBrief() {
		return 0;
	}

	@Override
	public EventType getType() {
		return EventType.EMPTY;
	}

	@Override
	public String getMissionArg() {
		// TODO 自动生成的方法存根
		return "";
	}
	
}
