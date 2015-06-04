//package cn.mxz.util;
//
//import cn.javaplus.net.IPFetcher;
//import cn.mxz.base.config.ServerConfig;
//import cn.mxz.util.debuger.Debuger;
//
//
//public class IPChecker implements Runnable {
//
//	@Override
//	public void run() {
//
//		String myIP;
//
//		try {
//
//			myIP = IPFetcher.getIP();
//
//		} catch (Throwable e) {
//
//			Debuger.debug("IPChecker.run() IP地址验证失败");
//
//			return ;
//		}
//
//		final String spIP = IPFetcher.getIP(ServerConfig.getConfig().getIp());
//
//		if(!spIP.equals(myIP)) {
//			Debuger.error("请注意，SP库上的IP地址 和本机IP地址不匹配，本机IP:" + myIP + " SP库上配置的IP:" + spIP);
//		} else {
//			Debuger.info("IP地址验证通过!");
//		}
//	}
//
//}
