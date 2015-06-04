package experiment;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IConnection.FlushMode;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.IHandler;
import org.xsocket.connection.IIdleTimeoutHandler;
import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.Server;

import define.SystemCfg;

/**
 * 测试在onData函数中，主动close连接，会否导致系统随后自动调用onDisconnect函数
 * 结论：
 * 		会自动调用
 * @author liukun
 * 2012-8-31 下午01:50:01
 */

class CloseConnectionTestHandle   implements IDataHandler ,IConnectHandler ,IIdleTimeoutHandler, IDisconnectHandler{

	@Override
	public boolean onDisconnect(INonBlockingConnection connection)
			throws IOException {
		System.out.println( "onDisconnect" );
		return false;
	}

	@Override
	public boolean onIdleTimeout(INonBlockingConnection connection)
			throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onConnect(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			MaxReadSizeExceededException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection connection)
			throws IOException, BufferUnderflowException,
			ClosedChannelException, MaxReadSizeExceededException {
		
		int available = connection.available();
		System.out.println( "available = " + available);
		connection.close();
		return false;
	}
	
}
public class CloseConnectionTest extends Server{

	public CloseConnectionTest( InetAddress address, int port, IHandler handler ) throws UnknownHostException, IOException {
		super(address, port, handler);
		setFlushmode( FlushMode.ASYNC );
		setIdleTimeoutMillis( 1000000000 );
	}


	public static void main(String[] args) throws IOException {
		InetAddress address = InetAddress.getByName( "localhost" );
		
		CloseConnectionTest server = new CloseConnectionTest( address, SystemCfg.PORT, new CloseConnectionTestHandle() );
		
		
		
		server.start();
		
		server.close();
		
	}
}
