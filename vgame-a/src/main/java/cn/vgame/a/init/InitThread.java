package cn.vgame.a.init;

import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.javaplus.web.WebContentFethcer;
import cn.vgame.a.Server;
import cn.vgame.a.config.GameProperties;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.MongoDbProperties;
import cn.vgame.a.log.MongoDbLogOutput;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.FileLogger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public final class InitThread extends Thread {

	public void run() {
		initMongoDb();
		setLogToDb();
		initGameXml();
		Turntable.getInstance().init();
		printSucessfulMessage();
	}

	private void printSucessfulMessage() {
		Log.d("server init successful");
		Log.d(getString("dbPort"));
		Log.d(getString("dbName"));
		Log.d(getString("dbPath"));
		Log.d(getString("tokenKey"));
		Log.d(getString("host"));
		Log.d(getString("webContextRoot"));
		Log.d(getString("zoneId"));
		Log.d(getString("serverIdentity"));
		Log.d(getString("isDebug"));
		Log.d(getString("isShowZfb"));
		Log.d(getString("logFilePath"));
	}

	private Object getString(String key) {
		return key + ":" + Server.getConfig().getString(key);
	}

	private void setLogToDb() {
//		Out out = new MongoDbLogOutput();
		Out out = new FileLogger(Server.getConfig().getString("logFilePath"));
		Log.setOut(out);
		Log.setErr(out);
	}

	private void initGameXml() {
		String path = Server.getConfig().getString("gameXmlPath");
		final String content = WebContentFethcer.get("utf8", path);
		Server.getXml().init(content);
		Log.d("init game.xml over");
	}

	private void initMongoDb() {

		String url = GameProperties.getString("gameConfigPath");
		url = url.replaceAll("SERVER_IDENTITY", Server.getIdentity());

		
		final String content = WebContentFethcer.get("utf8", url);
		
		Log.d(url);
		Log.d(content);
		
		final JSONObject o = JSON.parseObject(content);
		Server.getConfig().init(o);

		Daos.setProperties(new MongoDbPropertiesImpl());
		Log.d("init mongodb over");
	}

	private final class MongoDbPropertiesImpl implements MongoDbProperties {

		private MongoDbPropertiesImpl() {
		}

		@Override
		public int getPort() {
			return Server.getConfig().getInt("dbPort");
		}

		@Override
		public String getName() {
			return Server.getConfig().getString("dbName");
		}

		@Override
		public String getHost() {
			return Server.getConfig().getString("dbPath");
		}
	}
}