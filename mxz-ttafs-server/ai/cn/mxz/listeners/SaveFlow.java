package cn.mxz.listeners;

import java.util.List;

import cn.javaplus.compressor.ZLib;
import cn.javaplus.comunication.Response;
import cn.mxz.city.City;
import cn.mxz.events.AfterRequestEvent;
import cn.mxz.events.Listener;
import cn.mxz.util.flow.FlowManager;
import cn.mxz.util.flow.FlowObjects;

import com.lemon.commons.socket.ISocket;

//保存用户此次通信流量
public class SaveFlow implements Listener<AfterRequestEvent> {

	@Override
	public void onEvent(AfterRequestEvent event) {
		List<Response> r = event.getResponses();
		for (Response rs : r) {

			ISocket socket = event.getSocket();
			if (socket != null) {
				int compress = rs.compress(new ZLib());
				saveFlow(compress + 4, event.getUser());
				rs.send(socket);
			}
		}
	}

	private void saveFlow(int sizeThisTime, City user) {
		if (user != null) {
			FlowManager manager = FlowObjects.getManager(user.getId());
			manager.add(sizeThisTime);
		}
	}


}
