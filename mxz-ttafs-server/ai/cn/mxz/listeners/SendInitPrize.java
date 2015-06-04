package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.UserCreateEvent;
import define.D;

//赠送初始物品
public class SendInitPrize implements Listener<UserCreateEvent> {

	@Override
	public void onEvent(UserCreateEvent e) {
		e.getCity().getPrizeSender1().send(D.INIT_PRIZE);
	}

}
