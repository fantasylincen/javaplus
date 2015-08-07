package cn.vgame.b.init;

import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;
import cn.vgame.b.Server;
import cn.vgame.b.config.GameProperties;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MongoDbProperties;
import cn.vgame.b.log.MongoDbLogOutput;
import cn.vgame.share.FileLogger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public final class InitThread extends Thread {

	public void run() {
		initMongoDb();
		setLogToDb();
		initGameXml();
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
		Log.d("init log over");
	}

	private void initGameXml() {

		Log.d("init game.xml ...");
		final String content = Server.getKeyValueForever().getString("GAME_XML");
		if(content == null)
			throw new RuntimeException("请上传最新配置表");
		Server.getXml().init(content);
		Log.d("init game.xml over");
	}

	private void initMongoDb() {

		String url = GameProperties.getString("gameConfigPath");
		url = url.replaceAll("SERVER_IDENTITY", Server.getIdentity());
		
		Log.d(url);

		final String content = WebContentFethcer.get("utf8", url);

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