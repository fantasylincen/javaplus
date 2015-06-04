package cn.javaplus.proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.javaplus.proxy.http.Config;
import cn.javaplus.proxy.http.HttpProxy;
import cn.javaplus.proxy.socket5.SocketThread;
import cn.javaplus.util.Closer;

public class App {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startHttpProxyServer();
	}

	static void startHttpProxyServer() {

		try {
			HttpProxy proxy = new HttpProxy(new ServerSocket(Config.PORT));
			proxy.start();
			System.out.println("Http Proxy is running!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void startSocket5ProxyServer() {
		ServerSocket ss = null;
		try {

			ss = new ServerSocket(3152);
			while (true) {
				Socket s = null;
				try {
					s = ss.accept();
					new SocketThread(s).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.close(ss);
		}
	}
}