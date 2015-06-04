package cn.mxz.mission.type;

import cn.mxz.city.City;
import cn.mxz.mission.old.MapRandomEvent;
import cn.mxz.mission.old.MapRandomEventImpl;
import cn.mxz.yunyou.YunYouPlayer;
import define.D;

public class RandomEvent implements IEvent {

	private final int randomEventId;

	public RandomEvent( int randomEventId ) {
		this.randomEventId = randomEventId;
	}

	public RandomEvent(String arg) {
		this.randomEventId = Integer.parseInt( arg );
	}

	@Override
	public Object run(City user) {

		final MapRandomEvent randomTouched = new MapRandomEventImpl(randomEventId, user);

		final String responseEvent = randomTouched.responseEvent();

		if (randomTouched.getId() == D.YUN_YOU_XIAN_REN_EVENT_ID) {

			YunYouPlayer player = user.getYunYouPlayer();

			player.onEvent();
		}
		if(randomTouched.getId() == D.MOSHEN_EVENT_ID ) {

//			ShenmoManager.INSTANCE.onEvent( user );
			user.getUserShenmo().createShenmo();
		}
//		Debuger.debug("RandomEvent.run() Random事件");
		return null;
	}

	@Override
	public int getBrief() {
		return randomEventId;
	}

	@Override
	public EventType getType() {
		return EventType.RANDOM;
	}

	@Override
	public String getMissionArg() {
		return randomEventId+"";
	}



}
