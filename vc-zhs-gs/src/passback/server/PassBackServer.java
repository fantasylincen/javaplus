package passback.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.management.JMException;

import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IHandler;
import org.xsocket.connection.Server;
import org.xsocket.connection.IConnection.FlushMode;

/**
 * 充值回传 接受服务器
 * @author DXF
 *
 */
public class PassBackServer extends Server{

	public PassBackServer(InetAddress address, int port, IHandler handler) throws UnknownHostException, IOException {
		super(address, port, handler);
		setFlushmode( FlushMode.ASYNC );
		setIdleTimeoutMillis( 1000000000 );
	}

	
	
	public static void main( String[] args ) throws UnknownHostException, IOException, JMException{
		
		PassBackServer p_server = new PassBackServer( null, 8800, new PassBackHandler() );
		p_server.start();
		ConnectionUtils.registerMBean( p_server );
	}
	
}
