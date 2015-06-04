//package cn.mxz;
//
//import cn.mxz.base.server.ServerStartEvent;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//
//public class StartOrderProcesserListener implements Listener {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//		new OrderProcesser().start();
//	}
//
//	@Override
//	public Class<? extends ServerEvent> getEventListendClass() {
//		return ServerStartEvent.class;
//	}
//
//}
