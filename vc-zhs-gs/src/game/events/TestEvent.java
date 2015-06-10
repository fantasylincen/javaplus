package game.events;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.xsocket.connection.INonBlockingConnection;

import user.UserInfo;

public class TestEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		
	}

	public void run(INonBlockingConnection con, Object buf) throws IOException {
		
	}
}
