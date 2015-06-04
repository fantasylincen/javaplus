package cn.mxz.listeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;
import cn.mxz.base.server.Server;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.config.ConfigProperties;
import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;
import cn.mxz.system.Args;
import cn.mxz.util.debuger.SystemLog;

//启动停服监听服务器
public class StartStopServerListener implements Listener<ServerStartEvent> {

	public static class StopListener extends Thread {
		
		public StopListener() {
			setDaemon(true);
		}
		
		@Override
		public void run() {
			String key = "stopServerPort";
			String arg = Args.get(key);
			int port;

			if(arg == null) {
				
				IProperties p = Util.Property.getProperties(ConfigProperties.PATH);
				port = p.getInt(key);
			} else {
				port = new Integer(arg);
			}
			try {
				ServerSocket ss = new ServerSocket(port);
				SystemLog.debug("StartStopServerListener.StopListener.run() 停服监听程序启动成功! 端口:" + port);
				while(true) {
					Socket socket = ss.accept();
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = br.readLine();
					if("stop please".equals(line)) {
						SystemLog.debug("StartStopServerListener.StopListener.run() 服务器被管理员正常停止!");
						Server server = WorldFactory.getWorld().getServer();
						server.stop();
					}
				}
			} catch (IOException e) {
				throw new RuntimeException("StartStopServerListener.StopListener.run() 停服监听程序启动失败! 是不是端口已被占用? --  " + port, e);
			}
		}
	}

	@Override
	public void onEvent(ServerStartEvent e) {
		new StopListener().start();
	}


}
