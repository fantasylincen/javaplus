package user.agency;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.xsocket.connection.IHandler;
import org.xsocket.connection.Server;
import org.xsocket.connection.IConnection.FlushMode;

/**
 * 用户 代理
 * @author DXF
 *
 */
public class UserAgency extends Server  {

	public UserAgency(InetAddress address, int port, IHandler handler) throws UnknownHostException, IOException {
		super(address, port, handler);
		setFlushmode( FlushMode.ASYNC );
		setIdleTimeoutMillis( 1000000000 );
	}

	/**
	 * 初始化系统配置文件
	 */
	public void readAllCfg() {
		
	}
}
