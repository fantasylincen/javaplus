package http;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class RechargeServer extends Thread {

	private int port;

	public RechargeServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {

		try {
			int backLog = 5;
			InetSocketAddress inetSock = new InetSocketAddress(port);
			HttpServer httpServer = HttpServer.create(inetSock, backLog);

			{
				HttpContext c = httpServer.createContext("/getUserData", new GetUserDataHandler());
				c.getFilters().add(new ParameterFilter());
			}
			{
				HttpContext c = httpServer.createContext("/recharge", new RechargeHandler());
				c.getFilters().add(new ParameterFilter());
			}

			httpServer.setExecutor(null);
			httpServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
