package cn.javaplus.mxzrobot;

import cn.javaplus.log.Debuger;
import cn.javaplus.mxzrobot.httpserver.ChatHttpServer;


/**
 * Hello world!
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatHttpServer().start();
		Debuger.debug("vc-game-manager start success");
	}
}
