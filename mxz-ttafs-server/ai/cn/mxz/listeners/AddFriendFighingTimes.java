package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.friend.FriendBattle;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

public class AddFriendFighingTimes implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {

		Battle battle = event.getBattle();
		if (battle instanceof FriendBattle) {

			PlayerCamp under = event.getUnder();

			City city = under.getCity();
			UserCounterSetter uc = city.getUserCounterHistory();
			uc.add(CounterKey.FRIEND_ATTACK_TIMES, 1);
		}

	}
}
