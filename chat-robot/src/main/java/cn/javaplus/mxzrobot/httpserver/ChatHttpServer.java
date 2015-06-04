package cn.javaplus.mxzrobot.httpserver;

import java.net.InetSocketAddress;

import cn.javaplus.mxzrobot.define.ConfigProperties;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ChatHttpServer extends Thread {

	@Override
	public void run() {
		try {
			int backLog = 5;
			int port = ConfigProperties.getInt("httpServerPort");
			InetSocketAddress inetSock = new InetSocketAddress(port);
			HttpServer httpServer = HttpServer.create(inetSock, backLog);

			{
				HttpContext c = httpServer.createContext("/message", new ChatHandler());
				c.getFilters().add(new ParameterFilter());
			}
			{
				HttpContext c = httpServer.createContext("/", new IndexHandler());
				c.getFilters().add(new ParameterFilter());
			}
			{
				HttpContext c = httpServer.createContext("/login", new LoginHandler());
				c.getFilters().add(new ParameterFilter());
			}
			{
				HttpContext c = httpServer.createContext("/sendNotice", new SendNoticeHandler());
				c.getFilters().add(new ParameterFilter());
			}
			{
				HttpContext c = httpServer.createContext("/sendMail", new SendMailHandler());
				c.getFilters().add(new ParameterFilter());
			}
			
			httpServer.setExecutor(null);
			httpServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ChatHttpServer().start();
	}
}
