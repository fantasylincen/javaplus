package cn.mxz.listeners;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.comunication.Request;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.RequestSuccessEvent;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

public class LogRequestSuccess implements Listener<RequestSuccessEvent> {

	@Override
	public void onEvent(RequestSuccessEvent event) {
		Request request = event.getRequest();

		City u = event.getUser();
		ISocket s = event.getSocket();
		
		String log = "[" +request.getClassName() + "." + request.getMethodName() + "(" +  buildArgs(request.getArgsArray()) + ")|" + event.getRunTime() + "ms(" + tag(event.getRunTime()) + ")|";
		
		if (u != null) {

			log += "userId:" + u.getId();
		}

		log += "|socketId:" + s.getId();
		Debuger.debug("Service" , log);
	}

	private String buildArgs(Object[] as) {

		List<Object> args = Lists.newArrayList();

		for (Object arg : as) {

			args.add(arg);
		}

		Iterator<Object> it = args.iterator();

		StringBuilder sb = new StringBuilder();

		while (it.hasNext()) {

			Object arg = it.next();

			sb.append(arg + "");

			if (it.hasNext()) {

				sb.append(",");
			}
		}

		return sb + "";
	}
	
	private String tag(long w) {
		if (w < 20) {
			return "a";
		}
		if (w < 50) {
			return "b";
		}
		return "c";
	}
}
