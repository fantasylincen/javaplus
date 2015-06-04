package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.SocketOnDestroyEvent;
import cn.mxz.util.flow.FlowManager;
import cn.mxz.util.flow.FlowObjects;

//提交本次流量使用情况
public class CommitFlowThisTime implements Listener<SocketOnDestroyEvent> {

	@Override
	public void onEvent(SocketOnDestroyEvent e) {
		City city = e.getUser();
		if(city != null) {
			FlowManager m = FlowObjects.getManager(city.getId());
			m.commit();
		}
	}

}
