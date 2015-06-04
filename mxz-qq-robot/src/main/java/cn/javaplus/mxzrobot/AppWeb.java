package cn.javaplus.mxzrobot;

import cn.javaplus.log.Debuger;
import cn.javaplus.mxzrobot.httpserver.ChatHttpServer;


/**
 * Hello world!
 */
public class AppWeb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ChatHttpServer().start();
		Debuger.debug("AppWeb.main() 启动成功");
	}
}
