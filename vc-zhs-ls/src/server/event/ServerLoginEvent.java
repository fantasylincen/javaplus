package server.event;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import server.ServerManager;
import events.EventBase;

public class ServerLoginEvent extends EventBase{

	@Override
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		ServerManager.getInstance().connect( con, buf );
		
		// 开始ping
//		PingEvent p = (PingEvent) Event.PING_EVENT.getEventInstance();
//		p.run( con, SystemTimer.currentTimeSecond() + "" );
	}

	@Override
	public void run( INonBlockingConnection con, String... args ) throws IOException {
		
	}


}
