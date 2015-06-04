package cn.mxz.listeners;

import java.io.IOException;
import java.net.UnknownHostException;

import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;

import com.linekong.platform.protocol.erating.server.RechargeServer;

public class StartRechargeServer implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		// TODO 自动生成的方法存根
		try {
			new RechargeServer().run();
		} catch (UnknownHostException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

	}

}
