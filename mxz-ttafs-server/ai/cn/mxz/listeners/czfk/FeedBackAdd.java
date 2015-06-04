package cn.mxz.listeners.czfk;

import cn.mxz.city.City;
import cn.mxz.events.Listener;

import com.linekong.platform.protocol.erating.server.RechargeEvent;

public class FeedBackAdd implements Listener<RechargeEvent>{

	@Override
	public void onEvent(RechargeEvent e) {
		City user = e.getUser();
		int count = e.getRechargeCount();

		user.getFeedBackManager().addOnActivityOpen(count);
		user.getFeedBackManager2().addOnActivityOpen(count);
	}

}
