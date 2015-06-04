
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import mongo.gen.MongoGen.Daos;

import org.apache.log4j.PropertyConfigurator;

import cn.mxz.Loader;
import cn.mxz.base.MainArgs;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.logserver.LogServer;
import cn.mxz.base.server.AIServer;
import cn.mxz.base.server.MinaServer;
import cn.mxz.base.server.MongoCollectionFetcher;
import cn.mxz.base.server.Server;
import cn.mxz.base.servertask.ServerTask;
import cn.mxz.base.servertask.ServerTaskImpl;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.events.Events;
import cn.mxz.system.Args;
import cn.mxz.system.GameSystem;
import cn.mxz.util.Factory;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;
import cn.mxz.util.sencitive.SencitiveConfig;

import com.linekong.platform.protocol.erating.LineKongServerPassword;

import db.GameDB;
import db.dao.impl.DaoFactory;

public class App {

	private static Server server;

	public static void main(String[] args) {

		MainArgs.setArgs(args);
		PropertyConfigurator.configure("res/log4j.properties"); // 初始化日志配置

		Args.args = args;

		Factory.get("userService");// test

		ServerConfig.init(args); // 根据main输入参数,决定服务器ID,运行类型等
		LineKongServerPassword.init(args);

		Events.getInstance().loadListeners("cn.mxz.listeners");

		// 程序启动
		if (runLogServerOnly(args)) {

			new LogServer().start(); // 启动日志分析服务器

			SystemLog.debug("AILauncher.main() 日志服务器启动成功");
			return;
		}

		Loader.loadAll(); // 读取所有配置表
		JsonChecker.check();

		Debuger.init(args); // 调试器初始化

		SencitiveConfig.init(); // 敏感词

		DaoFactory.setFetcher(GameDB.getInstance());

		Daos.setCollectionFetcher(new MongoCollectionFetcher());

		WorldFactory.getWorld();// 加载世界

		final ServerTask task = ServerTaskImpl.getInstance();

		task.start(); // 启动服务器所有任务

		if (isMina(args)) {
			server = new MinaServer(ServerConfig.getGamePort()); // Mina游戏逻辑服务器启动
		} else {
			server = new AIServer(ServerConfig.getGamePort()); // 普通游戏逻辑服务器启动
		}

		GameSystem.getInstance().setServer(server);

		server.start();

//		setOut();

		// for (int i = 0; i < 10000; i++) {
		// Debuger.debug("test " + i);
		// }

		System.out.println("服务器启动成功");
	}

	private static void setOut() {

		File file = new File("systemlog");
		if (!file.exists()) {
			file.mkdir();
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String format = sf.format(new Date(System.currentTimeMillis()));
		File out = new File("systemlog/out-" + format + ".log");
		File err = new File("systemlog/err-" + format + ".log");

		create(out);
		create(err);

		try {
			PrintStream outp = new PrintStream(out);
			PrintStream oute = new PrintStream(err);
			System.setOut(outp);
			System.setErr(oute);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static void create(File out) {
		if (!out.exists()) {
			try {
				out.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 是否至启动日志服务器
	 * 
	 * @param args
	 * @return
	 */
	private static boolean runLogServerOnly(String[] args) {

		for (String string : args) {

			if (string.contains("-logServerOnly")) {

				return true;
			}
		}

		return false;
	}

	private static boolean isMina(String[] args) {

		for (String a : args) {

			if (a.trim().toLowerCase().equals("-mina")) {

				return true;
			}
		}

		return false;
	}

	public static void start() {

		main(Args.args);
	}

	public static Server getServer() {
		return server;
	}
}
