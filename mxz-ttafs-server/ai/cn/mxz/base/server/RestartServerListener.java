//package cn.mxz.base.server;
//
//import cn.mxz.base.telnet.command.system.RestartServerEvent;
//import cn.mxz.base.telnet.command.system.StopServer;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//
//
//public class RestartServerListener implements Listener {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		Runtime.getRuntime().addShutdownHook(new Thread(){
//
//			@Override
//			public void run() {
//
//				AILauncher.start();
//			}
//		});
//
//		new StopServer().run(null);
//	}
//
//	@Override
//	public Class<?> getEventListendClass() {
//		return RestartServerEvent.class;
//	}
//
//}
